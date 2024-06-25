package com.example.shoestore.Domain.Service.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Model.User.User;

public interface CartService {
    void createCart(User user);
    Cart getCart(String userEmail) throws Exception;
    Cart addItem(String userEmail, CartItem item) throws Exception;
    Cart removeItem(String userEmail, String productId)throws Exception;
    Cart updateItemQuantity(String userEmail, String productId, int quantity)throws Exception;
    void clearCart(String userEmail)throws Exception;
}
