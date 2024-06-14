package com.example.shoestore.Domain.DTO;

import java.util.List;
import java.util.Map;

public record ShoeDTO(
                    String id,
                    Integer size,
                  String gender,
                  List<String> color,
                  String name,
                  String brand,
                  String categories,
                  Double price,
                  Byte image,
                  String description,
                  Map<String, Integer> shoeQuantity) { }
