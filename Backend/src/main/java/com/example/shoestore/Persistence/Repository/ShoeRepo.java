package com.example.shoestore.Persistence.Repository;


import com.example.shoestore.Domain.DTO.ShoeDTOMapper;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShoeRepo extends MongoRepository<Shoe, Long> {

}
