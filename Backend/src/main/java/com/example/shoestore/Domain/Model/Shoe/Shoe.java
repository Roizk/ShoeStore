package com.example.shoestore.Domain.Model.Shoe;

import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "shoes")
public class Shoe {
    @Id
    private String id;

    private String name;
    private Double price;
    private Byte[] image;
    private String description;
    private List<Number> size;
    private List<String> color;

    @DBRef
    private Brand brand;

    @DBRef
    private Category category;

    @DBRef
    private Gender gender;

    private Map<String, Integer> inventory; // size_id -> quantity

    // Constructors, getters, and setters

    public Shoe(String name, Double price, Byte[] image, String description, List<Number> size) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.size= size;
        this.color= color;
    }

    public List<Number> getSize() {
        return size;
    }

    public void setSize(List<Number> size) {
        this.size = size;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}