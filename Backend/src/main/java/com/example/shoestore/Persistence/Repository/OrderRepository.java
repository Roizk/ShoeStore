package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

}
