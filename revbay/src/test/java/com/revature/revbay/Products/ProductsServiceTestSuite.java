package com.revature.revbay.Products;

import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsRepository;
import com.revature.revbay.products.ProductsService;
import com.revature.revbay.user.User;
import com.revature.revbay.util.enums.Category;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import com.revature.revbay.util.exceptions.InvalidInputException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTestSuite {
    @Mock
    private ProductsRepository mockProductsRepository;
    @InjectMocks
    private ProductsService mockProductsService;

    @Test
    public void test_findAll_returns_list_of_products() {
        List<Products> mockProducts = Arrays.asList(new Products(), new Products());
        when(mockProductsRepository.findAll()).thenReturn(mockProducts);

        List<Products> result = mockProductsService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
    @Test
    public void test_findAll_throws_DataNotFoundException() {
        ProductsRepository productsRepository = Mockito.mock(ProductsRepository.class);
        ProductsService productsService = new ProductsService(productsRepository);

        Mockito.when(productsRepository.findAll()).thenReturn(Collections.emptyList());

        Assertions.assertThrows(DataNotFoundException.class, productsService::findAll);
    }
    @Test
    public void test_findProductsByUserId_returns_products_for_valid_user_id() {
        ProductsRepository productsRepository = Mockito.mock(ProductsRepository.class);
        ProductsService productsService = new ProductsService(productsRepository);

        int userId = 123;
        List<Products> mockProducts = Arrays.asList(new Products(), new Products());
        Mockito.when(productsRepository.findProductsByUserId(userId)).thenReturn(Optional.of(mockProducts));

        List<Products> result = productsService.findProductsByUserId(userId);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
    }
    @Test
    public void testCreate_InvalidData(){
        Products products = null;
        assertThrows(InvalidInputException.class,()->mockProductsService.create(products));
    }
    @Test
    public void create_InvalidProduct(){
        User user = null;
        Products products = new Products(-1,"Phone", Category.ELECTRONICS,user,-5,-20.0);
        assertThrows(InvalidInputException.class,()->mockProductsService.create(products));
    }
    @Test
    public void testDelete_validProduct(){
        User user = new User(1,"asaf","ahmed","asaf@gmail.com","1234", User.UserType.SELLER);
        Products products = new Products(1,"phone",Category.ELECTRONICS,user,5,20.0);
        boolean actual = mockProductsService.delete(products);
        assertTrue(actual);
        verify(mockProductsRepository,times(1)).delete(products);

    }
    @Test
    public void update_product_successfully_when_product_exists() {

        Products mockProduct = new Products(1, "MockProduct", Category.GENERAL, new User(), 10, 100.0);


        Mockito.when(mockProductsRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockProduct));


        boolean result = mockProductsService.update(mockProduct);


        Assertions.assertTrue(result);
    }





}
