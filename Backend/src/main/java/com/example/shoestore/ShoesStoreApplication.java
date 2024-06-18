package com.example.shoestore;

import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Gender.Gender;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Persistence.Repository.ShoeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
public class ShoesStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoesStoreApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ShoeRepository shoeRepository) {
        return args -> {
            // Tạo và lưu giày
            Shoe shoe1 = new Shoe(
                    null,
                    "Air Max 90",
                    120.99,
                    null,
                    "Classic Nike Air Max 90.",
                    Arrays.asList(42, 43, 44),
                    Arrays.asList("Black", "White"),
                    new Brand("1", "Nike"),
                    new Category("1", "Running"),
                    new Gender("1", "Male"),
                    new HashMap<>()
            );

            Shoe shoe2 = new Shoe(
                    null,
                    "UltraBoost 21",
                    180.00,
                    null,
                    "Adidas UltraBoost 21.",
                    Arrays.asList(38, 39, 40),
                    Arrays.asList("Pink", "Purple"),
                    new Brand("2", "Adidas"),
                    new Category("1", "Running"),
                    new Gender("2", "Female"),
                    new HashMap<>()
            );

            shoeRepository.saveAll(Arrays.asList(shoe1, shoe2));

            System.out.println("Dữ liệu mẫu đã được thêm vào MongoDB.");
        };
    }

}
