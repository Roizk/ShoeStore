package com.example.shoestore.Domain.Service.Shoe;

import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.Model.Shoe.Shoe;

import java.util.List;

public interface ShoeService {
    List<ShoeDTO> getAll();
    List<Shoe> search(String keyword);
}
