package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Address.Address;
import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Model.Order.OrderItem;

import java.util.List;

public record OrderRequest (List<CartItem> items,
                            Address address,
                            String paymentMethod
                            )
{ }
