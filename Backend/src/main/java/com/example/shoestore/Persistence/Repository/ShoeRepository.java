package com.example.shoestore.Persistence.Repository;


import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {
    List<Shoe> findByNameContainingIgnoreCase(String name);
    default Shoe findByInventoryId(String inventoryItemId)
    {
        return findAll().stream()
                .filter(shoe -> shoe.getInventory().stream()
                        .anyMatch(item -> item.getId().equals(inventoryItemId)))
                .findFirst()
                .orElse(null);
    }
}
