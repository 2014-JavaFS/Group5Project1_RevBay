package com.revature.revbay.transactions;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//JPA
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @SequenceGenerator(name = "TransactionNumberGen", initialValue=1)
    @GeneratedValue(generator = "TransactionNumberGen")
    //Declaring Variables
    private int transactionID;
    @ManyToOne
    @JoinColumn(name="user_id")
    private int sellerID;
    @ManyToOne
    @JoinColumn(name="user_id")
    private int buyerID;
    private BigDecimal totalPrice;
    private String destination;
    //Might need to be changed to a different Date Type
    private Date currentTime;


    public Transactions(){}

    public Transactions(int transactionID, int sellerID, int buyerID, BigDecimal totalPrice, String destination, Date currentTime){
        this.transactionID=transactionID;
        this.sellerID=sellerID;
        this.buyerID=buyerID;
        this.totalPrice=totalPrice;
        this.destination=destination;
        this.currentTime=currentTime;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionID=" + transactionID +
                ", sellerID=" + sellerID +
                ", buyerID=" + buyerID +
                ", totalPrice=" + totalPrice +
                ", destination='" + destination + '\'' +
                ", currentTime=" + currentTime +
                '}';
    }


}
