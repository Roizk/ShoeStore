package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Cart.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {
}
