package com.revature.revbay.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;
    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }
    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        return ResponseEntity.status(200).body(productsService.findAll());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Products>> getProductsByUserId(@PathVariable int userId){
        return ResponseEntity.status(200).body(productsService.findProductsByUserId(userId));
    }
    @PostMapping
    public ResponseEntity<Products> createNewProducts( @RequestBody Products products){
        return ResponseEntity.status(200).body(productsService.create(products));
    }
    @PutMapping
    public ResponseEntity<Boolean> updateProductInfo(@RequestBody Products products){
        return ResponseEntity.status(200).body(productsService.update(products));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Products> getByProductId(@PathVariable int id){
        return ResponseEntity.status(200).body(productsService.findById(id));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> deleteProducts(@RequestBody Products products){
        return ResponseEntity.status(200).body(productsService.delete(products));
    }
}
