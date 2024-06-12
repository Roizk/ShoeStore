package com.example.shoestore.Domain.Model.Order;

public class OrderItem {
    private String shoeId;
    private Integer quantity;
    private String size;
    private Double priceAtPurchase;

    public OrderItem(String shoeId, Integer quantity, String size, Double priceAtPurchase) {
        this.shoeId = shoeId;
        this.quantity = quantity;
        this.size = size;
        this.priceAtPurchase = priceAtPurchase;
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

    public Double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(Double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

}