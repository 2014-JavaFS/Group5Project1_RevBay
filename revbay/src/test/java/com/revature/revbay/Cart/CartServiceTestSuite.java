package com.revature.revbay.Cart;

import com.revature.revbay.cart.Cart;
import com.revature.revbay.cart.CartRepository;
import com.revature.revbay.cart.CartService;
import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsRepository;
import com.revature.revbay.products.ProductsService;
import com.revature.revbay.user.User;
import com.revature.revbay.util.enums.Category;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import com.revature.revbay.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTestSuite {
    @Mock
    private CartRepository mockCartRepository;

    @InjectMocks
    private CartService mockCartService;

    @Test
    public void test_findAll_returns_list_of_cart() {
        List<Cart> mockCart = Arrays.asList(new Cart(), new Cart());
        when(mockCartRepository.findAll()).thenReturn(mockCart);

        List<Cart> result = mockCartService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void test_findAll_throws_DataNotFoundException() {
        CartRepository cartRepository = Mockito.mock(CartRepository.class);
        CartService cartService = new CartService(cartRepository);

        Mockito.when(cartRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(DataNotFoundException.class, cartService::findAll);
    }

    @Test
    public void test_findCartByUserId_returns_cart_for_valid_user_id() {
        CartRepository cartRepository = Mockito.mock(CartRepository.class);
        CartService cartService = new CartService(cartRepository);

        int userId = 123;
        List<Cart> mockCart = Arrays.asList(new Cart(), new Cart());
        Mockito.when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(mockCart));

        List<Cart> result = cartService.findByUserId(userId);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testCreate_InvalidData(){
        Cart cart = null;
        assertThrows(InvalidInputException.class,()->mockCartService.create(cart));
    }

    @Test
    public void create_InvalidCart(){
        User user = null;
        Products products = null;
        Cart cart = new Cart(-123, products, user,  123, "ADDRESS");
        assertThrows(InvalidInputException.class,()->mockCartService.create(cart));
    }

    @Test
    public void testDelete_validCart(){
        User user = new User(1,"gilberto","gonzalez","mail@mail.com","Pass123", User.UserType.BUYER);
        Products products = new Products(1,"phone",Category.ELECTRONICS,user,5,20.0);
        Cart cart = new Cart(123, products, user,  123, "ADDRESS");

        boolean actual = mockCartService.delete(cart);

        assertTrue(actual);
        verify(mockCartRepository,times(1)).delete(cart);

    }
    @Test
    public void update_cart_successfully_when_cart_exists() {
        User user = new User(1,"gilberto","gonzalez","mail@mail.com","Pass123", User.UserType.BUYER);
        Products products = new Products(1,"phone",Category.ELECTRONICS,user,5,20.0);
        Cart mockCart = new Cart(123, products, user,  123, "ADDRESS");

        Mockito.when(mockCartRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCart));

        boolean result = mockCartService.update(mockCart);

        Assertions.assertTrue(result);
    }
}
