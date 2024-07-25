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





}
