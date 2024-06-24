package com.example.shoestore;

import com.example.shoestore.Domain.Model.Address.Address;
import com.example.shoestore.Domain.Model.Brand.Brand;
import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Category.Category;
import com.example.shoestore.Domain.Model.Coupon.Coupon;
import com.example.shoestore.Domain.Model.Gender.Gender;
import com.example.shoestore.Domain.Model.Order.Order;
import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import com.example.shoestore.Domain.Model.Shoe.Shoe;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Domain.Security.ApplicationConfig;
import com.example.shoestore.Persistence.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ShoesStoreApplication {
    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private CouponRepository couponRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShoesStoreApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(ShoeRepository shoeRepository) {
//
//        return args -> {
//            Brand brand1 = new Brand(
//                    null,
//                    "Nike"
//            );
//            Brand brand2 = new Brand(
//                    null,
//                    "Adidas"
//            );
//            brandRepository.saveAll(Arrays.asList(brand1, brand2));
//
//
//            Gender gender1 = new Gender(
//                    null,
//                    "Male"
//            );
//            Gender gender2 = new Gender(
//                    null,
//                    "Female"
//            );
//            Gender gender3 = new Gender(
//                    null,
//                    "Unisex"
//            );
//            genderRepository.saveAll(Arrays.asList(gender1,gender2,gender3));
//
//
//            Category category1 = new Category(null, "Running");
//            Category category2 = new Category(null, "Sneaker");
//            categoryRepository.saveAll(Arrays.asList(category1,category2));
//
//
//            Coupon coupon1 = new Coupon( null,
//
//                    "SAVE20",
//                    20.0,
//                    new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31"),
//                    50.0,
//                    Arrays.asList("prod123", "prod456"),
//                    100,
//                    1,
//                    true
//                    );
//            Coupon coupon2 = new Coupon( null,
//
//                    "SAVE50",
//                    50.0,
//                    new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31"),
//                    50.0,
//                    Arrays.asList("prod123", "prod456"),
//                    100,
//                    1,
//                    true
//            );
//            couponRepository.saveAll(Arrays.asList(coupon1,coupon2));
//
//
//            List<InventoryItem> inventory1 = new ArrayList<>();
//            inventory1.add(new InventoryItem(null,"Trắng", 41, 10));
//            inventory1.add(new InventoryItem(null,"Trắng", 42, 6));
//            inventory1.add(new InventoryItem(null,"Trắng", 43, 8));
//            inventory1.add(new InventoryItem(null,"Đen", 41, 16));
//            inventory1.add(new InventoryItem(null,"Đen", 42, 4));
//            inventory1.add(new InventoryItem(null,"Đen", 43, 18));
//            Shoe shoe1 = new Shoe(
//                    null,
//                    "Air Max 90",
//                    120.99,
//                    null,
//                    "Classic Nike Air Max 90.",
//                    Arrays.asList(42, 43, 44),
//                    brand2,
//                    category1,
//                    gender3,
//                    inventory1
//            );
//
//            List<InventoryItem> inventory2 = new ArrayList<>();
//            inventory2.add(new InventoryItem(null,"Trắng", 41, 10));
//            inventory2.add(new InventoryItem(null,"Trắng", 42, 6));
//            inventory2.add(new InventoryItem(null,"Trắng", 43, 8));
//            inventory2.add(new InventoryItem(null,"Đen", 41, 16));
//            inventory2.add(new InventoryItem(null,"Đen", 42, 4));
//            inventory2.add(new InventoryItem(null,"Đen", 43, 18));
//
//            Shoe shoe2 = new Shoe(
//                    null,
//                    "UltraBoost 21",
//                    180.00,
//                    null,
//                    "Adidas UltraBoost 21.",
//                    Arrays.asList(38, 39, 40),
//                    brand1,
//                    category2,
//                    gender1,
//                    inventory2
//            );
//
//            shoeRepository.saveAll(Arrays.asList(shoe1, shoe2));
//
//
//            Address address1 = new Address("123 Main St", "New York");
//            Address address2 = new Address("456 Oak Ave", "Los Angeles");
//
//            // Tạo giỏ hàng mẫu
////            Cart cart1 = new Cart();
////            Cart cart2 = new Cart();
//
//            // Tạo người dùng mẫu
//            User user1 = new User(
//                    null,
//                    "johndoe",
//                    "john@example.com",
//                    "password123",
//                    "John",
//                    "Doe",
//                    address1,
//                    null, // Không có đơn hàng
//                    null,
//                    "USER",
//                    true
//            );
//
//            User user2 = new User(
//                    null,
//                    "adminuser",
//                    "admin@example.com",
//                    "admin123",
//                    "Admin",
//                    "User",
//                    address2,
//                    null, // Không có đơn hàng
//                    null,
//                    "ADMIN",
//                    true
//            );
//            userRepository.saveAll(Arrays.asList(user1, user2));
//
//
//            System.out.println("Dữ liệu mẫu đã được thêm vào MongoDB.");
//        };
//    }

}
