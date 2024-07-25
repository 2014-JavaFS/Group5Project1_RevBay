package com.revature.revbay.cart;

import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsService;
import com.revature.revbay.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/products")
    private ResponseEntity<List<Products>> getAllProducts(int userId) {
        return ResponseEntity.ok(cartService.findAllProductsByUserId(userId));
    }

    @PutMapping
    private ResponseEntity<Boolean> putUpdateCart(@RequestBody Cart updatedCart) {
        return ResponseEntity.ok(cartService.update(updatedCart));
    }
}
