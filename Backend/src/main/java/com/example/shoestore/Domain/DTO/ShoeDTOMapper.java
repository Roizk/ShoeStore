package com.example.shoestore.Domain.DTO;

import com.example.shoestore.Domain.DTO.ShoeDTO;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

public class ShoeDTOMapper implements Function<Shoe, ShoeDTO> {
    @Override
    public ShoeDTO apply(Shoe shoe) {
        return new ShoeDTO(
                shoe.getId(),
                shoe.getSize(),
                shoe.getGender(),
                shoe.getColorId(),
                shoe.getName(),
                shoe.getBrand(),
                shoe.getCategory(),
                shoe.getPrice(),
                shoe.getImage(),
                shoe.getDescription(),
                shoe.getInventory()
        );
    }
}
