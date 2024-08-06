package com.revature.revbay.Cart;

import com.revature.revbay.cart.CartController;
import com.revature.revbay.cart.CartRepository;
import com.revature.revbay.cart.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class CartControllerIntegrationTest {
    @MockBean
    private CartService cartService;
    @MockBean
    private CartRepository cartRepository;
    @Autowired
    private CartController cartController;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenPostRequestCartWithValidCartThenCorrectResponse() throws Exception {
        String cartJson = "{\n" +
                "    \"activeCartItem\": 1,\n" +
                "    \"productId\": 1,\n" +
                "    \"quantity\": 2,\n" +
                "    \"userId\": 1,\n" +
                "    \"address\": \"Test\"\n" +
                "}";

            mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                .content(cartJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

}
