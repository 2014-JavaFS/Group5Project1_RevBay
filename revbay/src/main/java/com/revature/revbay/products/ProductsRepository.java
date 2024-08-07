package com.revature.revbay.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    @Query("FROM Products  p WHERE p.user.userId = :userId")
    Optional<List<Products>> findProductsByUserId(int userId);
}
