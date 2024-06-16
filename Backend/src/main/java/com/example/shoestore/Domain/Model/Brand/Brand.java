package com.example.shoestore.Domain.Model.Brand;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "brands")
public class Brand {
    @Id
    private String id;
    private String name;
    // Constructors, getters, and setters
}