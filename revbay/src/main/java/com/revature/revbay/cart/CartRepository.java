package com.revature.revbay.cart;

import com.revature.revbay.products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    //TODO fix this since wrong query
//    @Query("SELECT * FROM cart where user_id = :userId")
//    List<Products> findAllProductsByUserId(int userId);
}
