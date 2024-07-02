package com.example.shoestore.Presentation.Controller.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Coupon.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/client/coupon")
public class ClientCouponController {
    private final CouponService couponService;
    @GetMapping
    public ResponseEntity<ResponseObject> applyCoupon(@RequestParam String code, @RequestBody List<OrderItem> orderItems)
    {
        try{
            Double priceApplied = couponService.calculateDiscount(code,orderItems);
            return ResponseUtils.buildSuccessResponse(priceApplied, "Price after apply coupon");
        } catch (Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
