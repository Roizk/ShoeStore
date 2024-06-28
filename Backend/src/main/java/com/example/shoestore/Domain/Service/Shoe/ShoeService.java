package com.example.shoestore.Domain.Service.Shoe;

import com.example.shoestore.Domain.Model.Shoe.Shoe;

import java.util.List;
import java.util.Optional;

public interface ShoeService {
    List<Shoe> getAll();
    List<Shoe> search(String keyword);
    Shoe createShoe(Shoe shoe);
    void deleteShoe(String id);
    Shoe updateShoe(String id, Shoe shoeDetails);
    Shoe getShoeById(String id);
    Shoe findByInventoryId(String inventoryItemId);

}
