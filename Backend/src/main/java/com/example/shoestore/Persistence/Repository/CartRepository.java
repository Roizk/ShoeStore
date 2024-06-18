package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Cart.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart,Long> {
}
