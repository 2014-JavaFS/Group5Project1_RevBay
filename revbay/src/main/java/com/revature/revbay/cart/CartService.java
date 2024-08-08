package com.revature.revbay.cart;

import com.revature.revbay.dtos.CartResponseDTO;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Cart> findByUserId(int id) {
        List<Cart> carts = cartRepository.findByUserId(id).get();
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

    public CartResponseDTO createCart(Cart newCart) {
        Optional<Cart> cart = Optional.of(cartRepository.save(newCart));

        return cart.map(CartResponseDTO::new).get();
    }

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public boolean delete(int id) {
        cartRepository.deleteById(id);
        return true;
    }

}
