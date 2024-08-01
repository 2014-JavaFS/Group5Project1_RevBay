package com.revature.revbay.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.user.userId = :userId")
    Optional<List<Cart>> findByUserId(int userId);
}
