package com.revature.revbay.transactions;

import com.revature.revbay.dtos.TransactionRequestDTO;
import com.revature.revbay.products.Products;
import com.revature.revbay.user.User;
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
    @JoinColumn(name="seller_id",referencedColumnName = "userId",nullable = false)
    private Products sellerID;

    @ManyToOne
    @JoinColumn(name="buyer_id",referencedColumnName = "userId",nullable = false)
    private User buyerID;

    private int quantity;
    private BigDecimal totalPrice;
    private String destination;
    //Might need to be changed to a different Date Type
    //private Date currentTime;

    public Transactions(TransactionRequestDTO transactionRequestDTO){
        User user = new User();
        user.setUserId(transactionRequestDTO.getBuyerID());
        this.buyerID=user;
        Products products= new Products();
        products.setProductId(transactionRequestDTO.getSellerID());
        this.sellerID=products;
    }




}
