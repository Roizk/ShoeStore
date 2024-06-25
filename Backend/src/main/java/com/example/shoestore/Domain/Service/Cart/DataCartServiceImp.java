package com.example.shoestore.Domain.Service.Cart;

import com.example.shoestore.Domain.Model.Cart.Cart;
import com.example.shoestore.Domain.Model.Cart.CartItem;
import com.example.shoestore.Domain.Model.Shoe.InventoryItem;
import com.example.shoestore.Domain.Model.User.User;
import com.example.shoestore.Persistence.Repository.CartRepository;
import com.example.shoestore.Persistence.Repository.InventoryItemRepository;
import com.example.shoestore.Persistence.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("DataCartServiceImp")
@Primary
@RequiredArgsConstructor
public class DataCartServiceImp implements CartService{
    private final CartRepository cartRepository;

    private final UserRepository userRepository;
    private final InventoryItemRepository inventoryRepository;
    @Override
    public void createCart(User user) {
        Cart cart = cartRepository.save(new Cart());
        user.setCart(cart);
    }
    @Override
    public Cart getCart(String userEmail) throws Exception{
        Optional<User> user = userRepository.findByEmail(userEmail);
        if (user.isPresent()) {
            User existingUser = user.get();
            if (existingUser.getCart() == null) {
                Cart newCart = new Cart();
                newCart.setItems(new ArrayList<>());
                newCart = cartRepository.save(newCart);
                existingUser.setCart(newCart);
                userRepository.save(existingUser);
            }
            return existingUser.getCart();
        } else {
            throw new Exception("User email: "+userEmail+" not found");
        }
    }
    @Override
    public Cart addItem(String userEmail, CartItem item) throws Exception {
        Cart cart = getCart(userEmail);
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        InventoryItem inventoryItem = inventoryRepository.findById(item.getInventory().getId())
                .orElseThrow(() -> new Exception("Inventory item not found"));
        if (inventoryItem.getQuantity() == 0) {
            throw new Exception("This item is out of stock");
        }
        if (inventoryItem.getQuantity() < item.getQuantity()) {
            throw new Exception("Not enough stock. Only " + inventoryItem.getQuantity() + " items available");
        }
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(i -> i.getInventory().getId().equals(item.getInventory().getId()))
                .findFirst();
        if (existingItem.isPresent()) {
            int newQuantity = existingItem.get().getQuantity() + item.getQuantity();
            if (newQuantity > inventoryItem.getQuantity()) {
                throw new Exception("Cannot add more items. Only " + (inventoryItem.getQuantity() - existingItem.get().getQuantity()) + " more available");
            }
            existingItem.get().setQuantity(newQuantity);
        } else {
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }
    @Override
    public Cart removeItem(String userEmail, String productId) throws Exception{
        Cart cart = getCart(userEmail);
        boolean removed = cart.getItems().removeIf(item -> item.getInventory().getId().equals(productId));

        if (!removed) {
            throw new Exception("Item not found in the cart");
        }

        return cartRepository.save(cart);
    }
    @Override
    public Cart updateItemQuantity(String userEmail, String productId, int quantity) throws Exception{
        Cart cart = getCart(userEmail);
        InventoryItem inventoryItem = inventoryRepository.findById(productId)
                .orElseThrow(() -> new Exception("Inventory item not found"));
        if (quantity > inventoryItem.getQuantity()) {
            throw new Exception("Not enough stock. Only " + inventoryItem.getQuantity() + " items available");
        }
        boolean updated = false;
        for (CartItem item : cart.getItems()) {
            if (item.getInventory().getId().equals(productId)) {
                item.setQuantity(quantity);
                updated = true;
                break;
            }
        }
        if (!updated) {
            throw new Exception("Item not found in the cart");
        }
        return cartRepository.save(cart);
    }
    @Override
    public void clearCart(String userEmail) throws Exception{
        Cart cart = getCart(userEmail);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
