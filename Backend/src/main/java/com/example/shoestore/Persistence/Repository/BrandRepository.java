package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Brand.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand,String> {
}
