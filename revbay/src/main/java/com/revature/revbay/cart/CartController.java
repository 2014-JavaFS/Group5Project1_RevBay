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
import java.util.Optional;

@CrossOrigin
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
    public ResponseEntity<List<Cart>> getAllCart(){
        return ResponseEntity.ok(cartService.findAll());
    }

    //might need to change to not retunr a repsonse entity.
    @GetMapping("/{id}")
    public ResponseEntity<List<Cart>> getByCartId(@PathVariable int id){
        return  ResponseEntity.ok(cartService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> createNewCart(@RequestBody CartRequestDTO cartRequestDTO){
        Products product = productsService.findById(cartRequestDTO.getProductId());
        User user = userService.findById(cartRequestDTO.getUserId());

        Cart cart = new Cart();
        cart.setProducts(product);
        cart.setUser(user);
        cart.setQuantity(cartRequestDTO.getQuantity());
        cart.setAddress(cartRequestDTO.getAddress());

        CartResponseDTO cartResponseDTO = cartService.createCart(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartResponseDTO);
    }

    @PutMapping
    private ResponseEntity<Boolean> putUpdateCart(@RequestBody Cart updatedCart) {
        return ResponseEntity.ok(cartService.update(updatedCart));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCart(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.delete(cart));
    }
}
