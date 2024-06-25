package com.example.shoestore.Domain.Response;

import com.example.shoestore.Domain.Model.Cart.CartItem;
import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse (
        List<CartItem> items
){
}
