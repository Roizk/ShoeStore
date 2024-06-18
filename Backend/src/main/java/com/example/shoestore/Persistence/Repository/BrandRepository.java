package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Brand.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand,Long> {
}
