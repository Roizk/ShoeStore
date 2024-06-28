package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends MongoRepository<InventoryItem,String> {
}
