package com.example.shoestore.Domain.Service.Order;

import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Model.Order.OrderStatus;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Request.OrderRequest;
import com.example.shoestore.Domain.Service.Authenticate.AuthenticateService;
import com.example.shoestore.Domain.Service.Coupon.CouponService;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import com.example.shoestore.Domain.Service.User.UserService;
import com.example.shoestore.Persistence.Repository.OrderRepository;
import com.example.shoestore.Persistence.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceIml implements OrderService{
    private final AuthenticateService authenService;
    private final UserService userService;
    private final CouponService couponService;
    private final ShoeService shoeService;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Order createOrder(HttpServletRequest request,OrderRequest orderRequest) throws Exception{
        String userId = userService.getUserId(authenService.getUserEmail());

        Order order = new Order();
        order.setItems(orderRequest.items().stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList()));
        order.setShippingAddress(orderRequest.address());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // Calculate subtotal
        Long subtotal = calculateSubtotal(order.getItems());
        order.setTotalAmount(subtotal);

        // Apply coupon if provided
        if (orderRequest.couponCode() != null && !orderRequest.couponCode().isEmpty()) {
            applyCoupon(order, orderRequest.couponCode());
        }
        Order savedOrder = orderRepository.save(order);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));
        if (user.getOrders() == null) {
            user.setOrders(new ArrayList<>());
        }
        user.getOrders().add(savedOrder);
        userRepository.save(user);

        // Create and return OrderResponse
        return savedOrder;
    }

    @Override
    public Optional<Order> getOrderById(String orderId) throws Exception{
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty())
        {
            throw new Exception("No Order Found");
        }else
            return order;
    }

    @Override
    public List<Order> getAllOrderByUserId(String userId) throws Exception{
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {

            throw new Exception("User not found with id: " + userId);
        } else {
            User user1 = user.get();
            return user1.getOrders();
        }
    }

    @Override
    public List<Order> getAllOrder() throws Exception{
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty())
        {
            throw new Exception("No Order Found");
        }else return orders;
    }


    private OrderItem convertToOrderItem(CartItem cartItem){
        Shoe shoe = shoeService.findByInventoryId(cartItem.getInventory().getId());
        OrderItem orderItem = new OrderItem();
        orderItem.setShoeId(shoe.getId());
        orderItem.setInventoryId(cartItem.getInventory().getId());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setSize(cartItem.getInventory().getSize());
        orderItem.setPriceAtPurchase(shoe.getPrice());
        return orderItem;
    }

    private Long calculateSubtotal(List<OrderItem> items) {
        return items.stream()
                .mapToLong(item -> Math.round(item.getPriceAtPurchase() * item.getQuantity()))
                .sum();
    }

    private void applyCoupon(Order order, String couponCode) throws Exception{
        Coupon coupon = couponService.getCouponByCode(couponCode);
        if (coupon == null || !coupon.getIsActive()) {
            throw new Exception("Invalid or inactive coupon");
        }

        if (order.getTotalAmount() < coupon.getMinimumPurchaseAmount()) {
            throw new Exception("Order total does not meet minimum purchase amount for this coupon");
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
