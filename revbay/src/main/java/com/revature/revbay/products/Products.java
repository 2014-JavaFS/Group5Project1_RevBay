package com.revature.revbay.products;

import com.revature.revbay.user.User;
import com.revature.revbay.util.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(unique = true,nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    //@Column(columnDefinition = "integer check (quantity>=0)")
    private int quantity;
    //@Column(columnDefinition = "double precision (price>0)")
    private Double price;
}
