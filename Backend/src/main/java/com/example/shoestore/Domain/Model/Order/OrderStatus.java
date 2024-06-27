package com.example.shoestore.Domain.Model.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public enum OrderStatus {
    PENDING, // Order has been received but not in processed
    SHIPPING, // Order is on the way to client
    DELIVERED, // Order has been delivered to client
    PAID, // Payment for order has been received
    PROCESSING, // Order is currently being prepared for shipment
    CANCELLED,// Order has been cancelled
    REFUNDED // Order has been refunded
}

