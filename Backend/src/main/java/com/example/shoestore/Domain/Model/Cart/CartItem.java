package com.example.shoestore.Domain.Model.Cart;

import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @DBRef
    private InventoryItem inventory;
    private Integer quantity;
}
