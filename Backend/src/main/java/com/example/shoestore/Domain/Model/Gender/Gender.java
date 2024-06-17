package com.example.shoestore.Domain.Model.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "genders")
public class Gender {
    @Id
    private String id;
    private String name; // "Male", "Female", "Unisex"



}
