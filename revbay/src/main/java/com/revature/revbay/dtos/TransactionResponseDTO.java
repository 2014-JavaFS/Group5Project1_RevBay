package com.revature.revbay.dtos;

import com.revature.revbay.transactions.Transactions;

import java.math.BigDecimal;

public class TransactionResponseDTO {
    private BigDecimal totalPrice;
    private int buyerID;
    private int sellerID;

    public TransactionResponseDTO(){}
    public TransactionResponseDTO(BigDecimal totalPrice, int buyerID, int sellerID){
        this.buyerID=buyerID;
        this.totalPrice=totalPrice;
        this.sellerID=sellerID;
    }

    public TransactionResponseDTO(Transactions currentTransaction){
        this.totalPrice=currentTransaction.getTotalPrice();
        this.buyerID=currentTransaction.getBuyerID().getUserId();
        this.sellerID=getSellerID();
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
}