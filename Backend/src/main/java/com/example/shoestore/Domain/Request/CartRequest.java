package com.example.shoestore.Domain.Request;

import com.example.shoestore.Domain.Model.Cart.CartItem;
import lombok.Builder;

import java.util.List;

@Builder
public record CartRequest (
        List<CartItem> items
){
}
