package com.example.shoestore.Domain.Service.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Service.Shoe.ShoeService;
import com.example.shoestore.Persistence.Repository.CouponRepository;
import com.example.shoestore.Persistence.Repository.InventoryItemRepository;
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


    public boolean isValidCoupon(String code, List<OrderItem> orderItems) {
        Coupon coupon = getCouponByCode(code);
        return coupon.getIsActive() &&
                new Date().before(coupon.getExpirationDate()) &&
                getTotalOrderAmount(orderItems) >= coupon.getMinimumPurchaseAmount() &&
                (coupon.getApplicableProducts().isEmpty() ||
                        orderItems.stream().anyMatch(item ->
                                coupon.getApplicableProducts().contains(item.getShoeId())));
    }
    private Double getTotalOrderAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity())
                .sum();
    }
    @Override
    public Double calculateDiscount(String code, List<OrderItem> orderItems) {
        Coupon coupon = getCouponByCode(code);
        if (!isValidCoupon(code,orderItems)) {
            throw new RuntimeException("Invalid coupon");
        }

        double discountableAmount = orderItems.stream()
                .filter(item -> shoeRepository.findById(item.getShoeId())
                        .map(shoe -> coupon.getApplicableProducts().isEmpty() ||
                                coupon.getApplicableProducts().contains(shoe.getId()))
                        .orElse(false))
                .mapToDouble(item -> item.getPriceAtPurchase() * item.getQuantity())
                .sum();

        return (discountableAmount * coupon.getDiscountPercentage()) / 100;
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
