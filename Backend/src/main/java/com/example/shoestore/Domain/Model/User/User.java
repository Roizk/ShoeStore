package com.example.shoestore.Domain.Model.User;

import com.example.shoestore.Domain.Model.Address.Address;
import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;

    private String userName;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private Address address;

    @DBRef
    private List<Order> orders;

    @DBRef
    private Cart cart;

    private String role;

    @Field("is_enabled")
    private boolean isEnabled = false;
}
