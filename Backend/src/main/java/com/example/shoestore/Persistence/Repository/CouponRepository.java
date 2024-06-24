package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Coupon.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends MongoRepository<Coupon,String> {
}
