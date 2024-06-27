package com.example.shoestore.Domain.Service.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Persistence.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponService{
    private final CouponRepository couponRepository;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
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
    public Coupon createCoupon(Coupon coupon) {
        validateCoupon(coupon);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(String id, Coupon couponDetails) {
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
    public boolean isValidCoupon(String code, Double orderTotal, List<String> productIds) {
        Coupon coupon = getCouponByCode(code);
        return coupon.getIsActive() &&
                new Date().before(coupon.getExpirationDate()) &&
                orderTotal >= coupon.getMinimumPurchaseAmount() &&
                (coupon.getApplicableProducts().isEmpty() ||
                        coupon.getApplicableProducts().stream().anyMatch(productIds::contains));
    }

    @Override
    public Double calculateDiscount(String code, Double orderTotal, List<String> productIds) {
        if (!isValidCoupon(code, orderTotal, productIds)) {
            throw new RuntimeException("Invalid coupon");
        }
        Coupon coupon = getCouponByCode(code);
        return (orderTotal * coupon.getDiscountPercentage()) / 100;
    }

    private void validateCoupon(Coupon coupon) {
        if (coupon.getDiscountPercentage() < 0 || coupon.getDiscountPercentage() > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        if (coupon.getExpirationDate().before(new Date())) {
            throw new IllegalArgumentException("Expiration date must be in the future");
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
