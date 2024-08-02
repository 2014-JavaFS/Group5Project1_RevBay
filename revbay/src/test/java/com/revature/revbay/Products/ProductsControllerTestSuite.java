package com.revature.revbay.Products;

import com.revature.revbay.products.ProductsController;
import com.revature.revbay.products.ProductsRepository;
import com.revature.revbay.products.ProductsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTestSuite {
    @MockBean
    private ProductsRepository productsRepository;
    @MockBean
    private ProductsService productsService;
    @Autowired
    private ProductsController productsController;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void whenPostRequestProductsWithValidProductsThenCorrectResponse() throws Exception{
        String productsJson = " {\n" +
                "        \"productId\": \"1\",\n" +
                "        \"name\": \"Dell Keyboard\",\n" +
                "        \"category\": \"ELECTRONICS\",\n" +
                "        \"user\":{\n" +
                "        \"userId\": 1,\n" +
                "        \"firstName\": \"May\",\n" +
                "        \"lastName\": \"Joon\",\n" +
                "        \"email\": \"joon3@email.com\",\n" +
                "        \"password\": \"securePass3\",\n" +
                "        \"userType\": \"SELLER\"\n" +
                "    },\n" +
                "    \"quantity\":5,\n" +
                "    \"price\":10.5\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(productsJson)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));

    }
}
