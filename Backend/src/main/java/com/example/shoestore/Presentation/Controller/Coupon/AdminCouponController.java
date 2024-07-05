package com.example.shoestore.Presentation.Controller.Coupon;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Response.ResponseObject;
import com.example.shoestore.Domain.Response.ResponseUtils;
import com.example.shoestore.Domain.Service.Coupon.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/coupon")
@AllArgsConstructor
public class AdminCouponController {
    private final CouponService couponService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllCoupon()
    {
        try {
            List<Coupon> response = couponService.getAllCoupons();
            return ResponseUtils.buildSuccessResponse(response,"Coupons retrieved successfully");
        } catch( Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        }
    }
    @GetMapping(params = "code")
    public ResponseEntity<ResponseObject> getCouponByCode(@RequestParam String code)
    {
        try{
            Coupon response =couponService.getCouponByCode(code);
            return ResponseUtils.buildSuccessResponse(response, "Coupon retrieved successfully");
        }catch( Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<ResponseObject> createCoupon(@RequestBody Coupon coupon)
    {
        try{
            Coupon response = couponService.createCoupon(coupon);
            return ResponseUtils.buildSuccessResponse(response, "Coupon created successfully");
        }catch( Exception ex) {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteCoupon(@RequestParam String id)
    {
        try{
            couponService.deleteCoupon(id);
            return ResponseUtils.buildSuccessResponse(null, "Coupon deleted successfully");
        } catch (Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<ResponseObject> updateCoupon(@RequestParam String id, @RequestBody Coupon coupon )
    {
        try{
            Coupon response = couponService.updateCoupon(id,coupon);
            return ResponseUtils.buildSuccessResponse(response, "Coupon updated successfully");
        } catch (Exception ex)
        {
            return ResponseUtils.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }


}
