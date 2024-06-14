package com.example.shoestore.Domain.Model.Shoe;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document(collection = "Shoe")
public class Shoe {

    @Id
    private String id;

    private Integer size;
    private String gender;
    private List<String> color;
    private String name;
    private String brand;
    private String categories;
    private Double price;
    private Byte image;
    private String description;
    private Map<String, Integer> shoeQuantity;

    public Shoe(){}

    public Shoe(String id,Integer size, String gender, List<String> color, String name, String brand, String categories, Double price, Byte image, String description, Map<String, Integer> shoeQuantity) {
        this.id=id;
        this.size = size;
        this.gender = gender;
        this.color = color;
        this.name = name;
        this.brand = brand;
        this.categories = categories;
        this.price = price;
        this.image = image;
        this.description = description;
        this.shoeQuantity = shoeQuantity;
    }


    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte getImage() {
        return image;
    }

    public void setImage(Byte image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Integer> getShoeQuantity() {
        return shoeQuantity;
    }

    public void setShoeQuantity(Map<String, Integer> shoeQuantity) {
        this.shoeQuantity = shoeQuantity;
    }


}
