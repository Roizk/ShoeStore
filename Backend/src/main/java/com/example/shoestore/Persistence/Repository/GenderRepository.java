package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Gender.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenderRepository extends MongoRepository<Gender,Long> {
}
