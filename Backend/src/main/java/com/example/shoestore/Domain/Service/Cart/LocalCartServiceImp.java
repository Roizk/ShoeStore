package com.example.shoestore.Domain.Service.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Model.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("LocalCartServiceImp")
@RequiredArgsConstructor
public class LocalCartServiceImp implements CartService{
    @Override
    public void createCart(User user) {

    }

    @Override
    public Cart getCart(String identifier) {
        return new Cart();
    }

    @Override
    public Cart addItem(String identifier, CartItem item) {
        return new Cart();
    }

    @Override
    public Cart removeItem(String identifier, String productId) {
        return new Cart();
    }

    @Override
    public Cart updateItemQuantity(String identifier, String productId, int quantity) {
        return new Cart();
    }

    @Override
    public void clearCart(String identifier) {
        // Do nothing, clearing happens in local storage
    }
}
