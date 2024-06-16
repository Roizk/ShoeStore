package com.example.shoestore.Domain.Model.Gender;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genders")
public class Gender {
    @Id
    private String id;
    private String name; // "Male", "Female", "Unisex"
    // Constructors, getters, and setters

    public Gender(String id, String name) {
        this.id = id;
        this.name = name;
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
}
