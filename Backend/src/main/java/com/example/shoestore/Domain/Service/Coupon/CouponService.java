package com.example.shoestore.Domain.Service.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponById(String id);
    Coupon getCouponByCode(String code);
    Coupon createCoupon(Coupon coupon);
    Coupon updateCoupon(String id, Coupon couponDetails);
    void deleteCoupon(String id);
    boolean isValidCoupon(String code, Double orderTotal, List<String> productIds);
    Double calculateDiscount(String code, Double orderTotal, List<String> productIds);
}
