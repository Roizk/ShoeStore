package com.example.shoestore.Domain.Service.Shoe;

import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ShoeService {
    Page<Shoe> getAllShoe(int page, int size)throws Exception;
    Page<Shoe> searchShoe(String keyword, int page, int size);
    Shoe createShoe(Shoe shoe);
    void deleteShoe(String id);
    Shoe updateShoe(String id, Shoe shoeDetails);
    Shoe getShoeById(String id);
    Shoe findByInventoryId(String inventoryItemId);

}
