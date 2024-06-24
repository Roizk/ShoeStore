package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Gender.Gender;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends MongoRepository<Gender,String> {
}
