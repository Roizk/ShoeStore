package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Color.Color;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColorRepository extends MongoRepository<Color,Long> {
}
