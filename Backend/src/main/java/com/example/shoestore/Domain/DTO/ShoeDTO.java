package com.example.shoestore.Domain.DTO;

import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;

import java.util.List;
import java.util.Map;

public record ShoeDTO(
                    String id,
                    List<Number> size,
                  Gender gender,
                  List<String> color,
                  String name,
                  Brand brand,
                  Category categories,
                  Double price,
                  Byte[] image,
                  String description,
                  Map<String, Integer> inventory) { }
