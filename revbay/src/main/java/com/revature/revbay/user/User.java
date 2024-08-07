package com.revature.revbay.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(columnDefinition = "varchar(20)")
    private String firstName;

    @Column(columnDefinition = "varchar(30)")
    private String lastName;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(40)")
    private String email;

    @Column(nullable = false, columnDefinition = "varchar(60)")
    private String password;

    @Column(columnDefinition = "varchar(6) default 'BUYER'")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public enum UserType {
        BUYER,
        SELLER
    }
}