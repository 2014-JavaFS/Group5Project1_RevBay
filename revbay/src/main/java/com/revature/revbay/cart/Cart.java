package com.revature.revbay.cart;

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
    @JoinColumn(name = "product_id")
    private int productId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private int userId;
    private int quantity;
    private String address;
}
