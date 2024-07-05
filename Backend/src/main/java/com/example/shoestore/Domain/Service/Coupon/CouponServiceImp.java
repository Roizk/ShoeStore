package com.example.shoestore.Domain.Service.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Service.Order.OrderService;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import com.example.shoestore.Persistence.Repository.CouponRepository;
import com.example.shoestore.Persistence.Repository.InventoryItemRepository;
import com.example.shoestore.Persistence.Repository.OrderRepository;
import com.example.shoestore.Persistence.Repository.ShoeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService{
    private final CouponRepository couponRepository;
    private final InventoryItemRepository inventoryRepository;
    private final ShoeRepository shoeRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon getCouponById(String id) {
        return couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    @Override
    public Coupon createCoupon(Coupon coupon) throws Exception{
        validateCoupon(coupon);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(String id, Coupon couponDetails) throws Exception{
        Coupon existingCoupon = getCouponById(id);
        updateCouponFields(existingCoupon, couponDetails);
        validateCoupon(existingCoupon);
        return couponRepository.save(existingCoupon);
    }

    @Override
    public void deleteCoupon(String id) {
        couponRepository.deleteById(id);
    }

    @Override
    public Long applyCoupon(String code, String orderId) throws Exception{
        Coupon coupon = getCouponByCode(code);
        Optional<Order> optionalOrder = orderService.getOrderById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = optionalOrder.get();
        if (!isValidCoupon(coupon, order)) {
            throw new RuntimeException("Invalid coupon");
        }

        long discount = calculateDiscount(coupon, order);
        long newTotalAmount = order.getTotalAmount() - Math.round(discount);

        order.setTotalAmount(newTotalAmount);
        order.setAppliedCouponCode(code);
        orderRepository.save(order);
        coupon.setUsageLimit(coupon.getUsageLimit()-1);

        return  newTotalAmount;
    }


    private boolean isValidCoupon(Coupon coupon,  Order order) {
        return coupon.getIsActive() &&
                new Date().before(coupon.getExpirationDate()) &&
                order.getTotalAmount() >= coupon.getMinimumPurchaseAmount() &&
                (coupon.getApplicableProducts().isEmpty() ||
                        order.getItems().stream().anyMatch(item ->
                                coupon.getApplicableProducts().contains(item.getShoeId())))&&
                (order.getAppliedCouponCode() == null || !order.getAppliedCouponCode().equals(coupon.getCode()));
    }

    private long calculateDiscount(Coupon coupon, Order order) {
        double discountableAmount = order.getItems().stream()
                .filter(item -> shoeRepository.findById(item.getShoeId())
                        .map(shoe -> coupon.getApplicableProducts().isEmpty() ||
                                coupon.getApplicableProducts().contains(shoe.getId()))
                        .orElse(false))
                .mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity())
                .sum();

        return (long) ((discountableAmount * coupon.getDiscountPercentage()) / 100);
    }

    private void validateCoupon(Coupon coupon) throws Exception{
        if (coupon.getDiscountPercentage() < 0 || coupon.getDiscountPercentage() > 100) {
            throw new Exception("Discount percentage must be between 0 and 100");
        }
        if (coupon.getExpirationDate().before(new Date())) {
            throw new Exception("Expiration date must be in the future");
        }
    }

    private void updateCouponFields(Coupon existingCoupon, Coupon couponDetails) {
        existingCoupon.setCode(couponDetails.getCode());
        existingCoupon.setDiscountPercentage(couponDetails.getDiscountPercentage());
        existingCoupon.setExpirationDate(couponDetails.getExpirationDate());
        existingCoupon.setMinimumPurchaseAmount(couponDetails.getMinimumPurchaseAmount());
        existingCoupon.setApplicableProducts(couponDetails.getApplicableProducts());
        existingCoupon.setUsageLimit(couponDetails.getUsageLimit());
        existingCoupon.setUsageLimitPerUser(couponDetails.getUsageLimitPerUser());
        existingCoupon.setIsActive(couponDetails.getIsActive());
    }
}
