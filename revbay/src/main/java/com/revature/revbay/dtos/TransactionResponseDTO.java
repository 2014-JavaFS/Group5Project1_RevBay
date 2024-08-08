package com.revature.revbay.dtos;

import com.revature.revbay.transactions.Transactions;

import java.math.BigDecimal;

public class TransactionResponseDTO {
    private BigDecimal totalPrice;
    private int buyerID;
    private int sellerID;



    private int quantity;

    public TransactionResponseDTO(){}

    public TransactionResponseDTO(BigDecimal totalPrice, int buyerID, int sellerID, int quantity){
        this.buyerID=buyerID;
        this.totalPrice=totalPrice;
        this.sellerID=sellerID;
        this.quantity=quantity;
    }

    public TransactionResponseDTO(Transactions currentTransaction){
        this.totalPrice=BigDecimal.valueOf(currentTransaction.getSellerID().getPrice() * currentTransaction.getQuantity());
        this.buyerID=currentTransaction.getBuyerID().getUserId();
        this.sellerID=currentTransaction.getSellerID().getProductId();
        this.quantity=currentTransaction.getQuantity();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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