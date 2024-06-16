package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CateRepo extends MongoRepository<Shoe, Long> {
}
