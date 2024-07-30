package com.revature.revbay.cart;

import com.revature.revbay.dtos.CartRequestDTO;
import com.revature.revbay.dtos.CartResponseDTO;
import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsService;
import com.revature.revbay.user.User;
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
    private final ProductsService productsService;
    private final UserService userService;

    @Autowired
    public CartController(CartService cartService, ProductsService productsService, UserService userService) {
        this.cartService = cartService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @GetMapping
    public List<Cart> getAllCart(){
        return cartService.findAll();
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createNewCart(@RequestBody CartRequestDTO cartRequestDTO){
        Products product = productsService.findById(cartRequestDTO.getProductId());
        User user = userService.findById(cartRequestDTO.getUserId());

        Cart cart = new Cart();
        cart.setProducts(product);
        cart.setUser(user);

        CartResponseDTO cartResponseDTO = cartService.createCart(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponseDTO);
    }

    @PutMapping
    private ResponseEntity<Boolean> putUpdateCart(@RequestBody Cart updatedCart) {
        return ResponseEntity.ok(cartService.update(updatedCart));
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getByCartId(@PathVariable int id){
        return ResponseEntity.ok(cartService.findById(id));
    }
    */

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCart(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.delete(cart));
    }
}
