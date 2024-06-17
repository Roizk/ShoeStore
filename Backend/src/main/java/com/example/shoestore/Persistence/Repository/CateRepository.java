package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CateRepository extends MongoRepository<Category, Long> {
}
