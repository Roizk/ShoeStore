package com.example.shoestore.Domain.Service.Order;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Model.Order.OrderStatus;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.OrderRequest;
import com.example.shoestore.Domain.Response.OrderResponse;
import com.example.shoestore.Domain.Service.Authenticate.AuthenticateService;
import com.example.shoestore.Domain.Service.Coupon.CouponService;
import com.example.shoestore.Domain.Service.User.UserService;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceIml implements OrderService{
    private final AuthenticateService authenService;
    private final UserService userService;
    private final CouponService couponService;
    @Override
    public OrderResponse createOrder(HttpServletRequest request, @RequestBody OrderRequest orderRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        String userId = userService.getUserId(currentUsername);

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderRequest.getItems().stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList()));
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // Calculate subtotal
        Long subtotal = calculateSubtotal(order.getItems());
        order.setTotalAmount(subtotal);

        // Apply coupon if provided
        if (orderRequest.getCouponCode() != null && !orderRequest.getCouponCode().isEmpty()) {
            applyCoupon(order, orderRequest.getCouponCode());
        }

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Create and return OrderResponse
        return createOrderResponse(savedOrder);
    }

    public String extractEmail(String token)
    {
        return authenService.extractEmail(token);
    }
    private OrderItem convertToOrderItem(OrderItemRequest itemRequest) {
        Shoe shoe = shoeService.getShoeById(itemRequest.getShoeId());
        OrderItem orderItem = new OrderItem();
        orderItem.setShoeId(itemRequest.getShoeId());
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setSize(itemRequest.getSize());
        orderItem.setPriceAtPurchase(shoe.getPrice());
        return orderItem;
    }

    private Long calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToLong(item -> Math.round(item.getPriceAtPurchase() * item.getQuantity()))
                .sum();
    }

    private void applyCoupon(Order order, String couponCode) {
        Coupon coupon = couponService.getCouponByCode(couponCode);
        if (coupon == null || !coupon.getIsActive()) {
            throw new RuntimeException("Invalid or inactive coupon");
        }

        if (order.getTotalAmount() < coupon.getMinimumPurchaseAmount()) {
            throw new RuntimeException("Order total does not meet minimum purchase amount for this coupon");
        }

        Long discount = calculateDiscount(order.getItems(), coupon);
        order.setAppliedCouponCode(couponCode);
        order.setTotalAmount(order.getTotalAmount() - discount);
    }

    private Long calculateDiscount(List<OrderItem> items, Coupon coupon) {
        return items.stream()
                .filter(item -> coupon.getApplicableProducts().contains(item.getShoeId()))
                .mapToLong(item -> Math.round(item.getPriceAtPurchase() * item.getQuantity() * coupon.getDiscountPercentage() / 100))
                .sum();
    }

}
