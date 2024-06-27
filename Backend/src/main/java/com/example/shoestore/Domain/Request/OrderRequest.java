package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Address.Address;
import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Order.OrderItem;

import java.util.List;

public record OrderRequest (List<OrderItem> items,
                            Address address,
                            String couponCode,
                            String paymentMethod
                            )
{ }
