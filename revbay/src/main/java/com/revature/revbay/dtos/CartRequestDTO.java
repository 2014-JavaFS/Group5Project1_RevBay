package com.revature.revbay.dtos;

public class CartRequestDTO {
    private int productId;
    private int userId;
    private int quantity;
    private String address;

    public CartRequestDTO() {
    }

    public CartRequestDTO(int productId, int userId, int quantity, String address) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.address = address;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
