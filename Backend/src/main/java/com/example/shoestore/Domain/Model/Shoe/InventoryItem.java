package com.example.shoestore.Domain.Model.Shoe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "inventories")
public class InventoryItem {
    @Id
    private String id;
    private String color;
    private Integer size;
    private Integer quantity;
}
