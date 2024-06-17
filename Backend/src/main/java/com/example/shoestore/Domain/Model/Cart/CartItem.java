package com.example.shoestore.Domain.Model.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private String shoeId;
    private Integer quantity;
    private String size;


}
