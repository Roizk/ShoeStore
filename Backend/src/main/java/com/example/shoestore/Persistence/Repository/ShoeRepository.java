package com.example.shoestore.Persistence.Repository;


import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, Long> {

}
