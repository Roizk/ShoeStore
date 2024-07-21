package com.example.shoestore.Persistence.Repository;


import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoeRepository extends MongoRepository<Shoe, String> {
    Page<Shoe> findAll(Pageable pageable);
    Optional<Shoe> findById(String id);
    Shoe save(Shoe shoe);
    void deleteById(String id);
    Page<Shoe> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    default Shoe findByInventoryId(String inventoryItemId)
    {
        return findAll().stream()
                .filter(shoe -> shoe.getInventory().stream()
                        .anyMatch(item -> item.getId().equals(inventoryItemId)))
                .findFirst()
                .orElse(null);
    }
}
