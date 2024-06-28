package com.example.shoestore.Domain.Service.Inventory;

import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Model.Order.OrderItem;
import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import com.example.shoestore.Persistence.Repository.InventoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImp implements InventoryService{
    private final InventoryItemRepository inventoryRepository;

    @Override
    @Transactional
    public void updateInventory(Order order) {
        for (OrderItem item : order.getItems()) {
            InventoryItem inventoryItem = inventoryRepository.findById(item.getInventoryId())
                    .orElseThrow(() -> new RuntimeException("Inventory item not found"));

            if (inventoryItem.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for item: " + item.getShoeId());
            }

            inventoryItem.setQuantity(inventoryItem.getQuantity() - item.getQuantity());
            inventoryRepository.save(inventoryItem);
        }
    }
}
