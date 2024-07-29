package com.revature.revbay.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
    Optional<List<Products>> findByUserId(int userId);
}
