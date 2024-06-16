package com.example.shoestore.Domain.Model.Category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Category {
    @Id
    private String id;
    private String name;
    // Constructors, getters, and setters
}
