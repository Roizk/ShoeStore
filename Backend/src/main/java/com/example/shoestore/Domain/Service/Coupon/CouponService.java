package com.example.shoestore.Domain.Service.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.OrderItem;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponByCode(String code);
    Coupon createCoupon(Coupon coupon)throws Exception;
    Coupon updateCoupon(String id, Coupon couponDetails)throws Exception;
    void deleteCoupon(String id)throws Exception;
    Long applyCoupon(String code, String orderId)throws Exception;
}
