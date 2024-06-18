package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponRepository extends MongoRepository<Coupon,Long> {
}
