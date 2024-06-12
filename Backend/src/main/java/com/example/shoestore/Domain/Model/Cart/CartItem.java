package com.example.shoestore.Domain.Model.Cart;

public class CartItem {
    private String shoeId;
    private Integer quantity;
    private String size;

    public CartItem(String shoeId, Integer quantity, String size) {
        this.shoeId = shoeId;
        this.quantity = quantity;
        this.size = size;
    }

    public String getShoeId() {
        return shoeId;
    }

    public void setShoeId(String shoeId) {
        this.shoeId = shoeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
