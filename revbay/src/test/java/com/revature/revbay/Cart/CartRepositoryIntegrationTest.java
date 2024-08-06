package com.revature.revbay.Cart;

import com.revature.revbay.cart.Cart;
import com.revature.revbay.cart.CartRepository;
import com.revature.revbay.products.Products;
import com.revature.revbay.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class CartRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testCartSaved() {
        Cart cart = new Cart();
        User user = new User();
        Products products = new Products();

        cart.setActiveCartItem(777);
        cart.setAddress("Test");
        cart.setQuantity(123);
        cart.setUser(user);
        cart.setProducts(products);

        Cart savedCart = cartRepository.save(cart);
        Assertions.assertEquals(777, savedCart.getActiveCartItem());
        Assertions.assertEquals("Test", savedCart.getAddress());
        Assertions.assertEquals(123, savedCart.getQuantity());
    }
}
