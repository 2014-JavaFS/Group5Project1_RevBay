package com.revature.revbay.cart;

import com.revature.revbay.products.Products;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll(){
        List<Cart> carts = cartRepository.findAll();
        if(carts.isEmpty()){
            throw new DataNotFoundException("No cart information found :(");
        }else {
            return carts;
        }
    }

    @Transactional
    public boolean update(Cart cartToUpdate) {
        cartRepository.saveAndFlush(cartToUpdate); // helps stop dirty-reads
        return true;
    }

//    @PostMapping
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

//    @DeleteMapping
    public boolean delete(Cart cart) {
        cartRepository.delete(cart);
        return true;
    }

}
