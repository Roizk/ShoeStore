package com.example.shoestore.Domain.Model.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;

    private String userId;
    private String sessionId;
    private List<CartItem> items;
    private Double subTotal;
    private Double totalAmount;
    private Date updatedAt;



}
