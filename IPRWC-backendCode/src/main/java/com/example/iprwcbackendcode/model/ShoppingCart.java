package com.example.iprwcbackendcode.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "productId")
    private UUID productId;

    @Column(name = "userId")
    private UUID userId;

    private int quantity;

    public ShoppingCart() {}

    public ShoppingCart(UUID userId, UUID productId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID product_id) {
        this.productId = product_id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID user_id) {
        this.userId = user_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
