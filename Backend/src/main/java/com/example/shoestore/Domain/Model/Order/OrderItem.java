    package com.example.shoestore.Domain.Model.Order;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderItem {
        private String shoeId;
        private Integer quantity;
        private Integer size;
        private long priceAtPurchase;
        private String inventoryId;

    }