package com.example.shoestore.Domain.Service.Inventory;

import com.example.shoestore.Domain.Model.Order.Order;

public interface InventoryService {
    void updateInventory(Order order);
}
