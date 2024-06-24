package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
