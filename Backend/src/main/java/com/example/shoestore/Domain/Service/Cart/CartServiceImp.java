package com.example.shoestore.Domain.Service.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Persistence.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService{
    private final CartRepository cartRepository;
    @Override
    public void createCart(User user) {
        Cart cart = cartRepository.save(new Cart());
        user.setCart(cart);
    }
}
