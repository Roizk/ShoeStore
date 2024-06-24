package com.example.shoestore.Domain.Model.Shoe;

import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "shoes")
public class Shoe {
    @Id
    private String id;

    private String name;
    private Double price;
    private Byte[] image;
    private String description;
    private List<Number> size;

    @DBRef
    private Brand brand;

    @DBRef
    private Category category;

    @DBRef
    private Gender gender;

    private List<InventoryItem> inventory;


}