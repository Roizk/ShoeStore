package com.example.shoestore.Domain.DTO;

import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ShoeDTOMapper implements Function<Shoe, ShoeDTO> {
    @Override
    public ShoeDTO apply (Shoe shoe)
    {
        return new ShoeDTO(
                shoe.getId(),
                shoe.getSize(),
                shoe.getGender(),
                shoe.getColor(),
                shoe.getName(),
                shoe.getBrand(),
                shoe.getCategories(),
                shoe.getPrice(),
                shoe.getImage(),
                shoe.getDescription(),
                shoe.getShoeQuantity()
                );
    }
}
