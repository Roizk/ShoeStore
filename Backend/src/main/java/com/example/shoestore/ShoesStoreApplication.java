package com.example.shoestore;

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
//
//    @Bean
//    CommandLineRunner commandLineRunner(ShoeRepository shoeRepository) {
//        return args -> {
//            // Tạo và lưu giày
//            Shoe shoe1 = new Shoe(null, 42, "Male", Arrays.asList("Black", "White"), "Air Max 90", "Nike", "Running", 120.99, null, "Classic Nike Air Max 90.", new HashMap<>());
//            Shoe shoe2 = new Shoe(null, 38, "Female", Arrays.asList("Pink", "Purple"), "UltraBoost 21", "Adidas", "Running", 180.00, null, "Adidas UltraBoost 21.", new HashMap<>());
//
//            shoeRepository.saveAll(Arrays.asList(shoe1, shoe2));
//
//            System.out.println("Dữ liệu mẫu đã được thêm vào MongoDB.");
//        };
//    }

}
