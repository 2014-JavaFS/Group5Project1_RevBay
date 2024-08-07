package com.revature.revbay.dtos;

import com.revature.revbay.cart.Cart;
import com.revature.revbay.products.Products;
import com.revature.revbay.user.User;

public class CartResponseDTO {
    private int activeCartItem;
    private Products products;
    private User user;
    private int quantity;
    private String address;

    public CartResponseDTO() {

    }

    public CartResponseDTO(int activeCartItem, Products products, User user, int quantity, String address) {
        this.activeCartItem = activeCartItem;
        this.products = products;
        this.user = user;
        this.quantity = quantity;
        this.address = address;
    }

    public CartResponseDTO(Cart cart) {
        this.activeCartItem = cart.getActiveCartItem();
        this.products = cart.getProducts();
        this.user = cart.getUser();
        this.quantity = cart.getQuantity();
        this.address = cart.getAddress();
    }

    public int getActiveCartItem() {
        return activeCartItem;
    }

    public void setActiveCartItem(int activeCartItem) {
        this.activeCartItem = activeCartItem;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
