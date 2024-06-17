package com.example.shoestore.Domain.Model.Color;
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
@Document(collection = "colors")
public class Color {
    @Id
    private String id;
    private String name;
    private String hexCode; // Có thể thêm mã màu hex


}