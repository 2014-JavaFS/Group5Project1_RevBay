package com.revature.revbay.dtos;

public class TransactionRequestDTO {
    private int buyerID;
    private int sellerID;
    private int quantity;

    public TransactionRequestDTO(){}

    public TransactionRequestDTO(int buyerID, int sellerID, int quantity){
        this.buyerID=buyerID;
        this.sellerID=sellerID;
        this.quantity=quantity;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
