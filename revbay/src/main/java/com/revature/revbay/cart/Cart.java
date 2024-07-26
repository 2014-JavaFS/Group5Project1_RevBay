package com.revature.revbay.cart;

import com.revature.revbay.products.Products;
import com.revature.revbay.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activeCartItem;
    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Products products;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    private int quantity;
    private String address;
}
