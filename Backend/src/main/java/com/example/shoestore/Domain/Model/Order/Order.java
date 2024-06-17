package com.example.shoestore.Domain.Model.Order;

import com.example.shoestore.Domain.Model.Address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String userId;
    private List<OrderItem> items;
    private Double totalAmount;
    private OrderStatus status;
    private Address shippingAddress;
    private String paymentMethod;
    private Date createdAt;
    private Date updatedAt;



}

