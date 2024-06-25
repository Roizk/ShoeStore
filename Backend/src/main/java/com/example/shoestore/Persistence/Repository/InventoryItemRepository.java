package com.example.shoestore.Persistence.Repository;

import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryItemRepository extends MongoRepository<InventoryItem,String> {
}
