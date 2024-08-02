package com.revature.revbay.Products;

import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsRepository;
import com.revature.revbay.user.User;
import com.revature.revbay.util.enums.Category;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class ProductsRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductsRepository productsRepository;
    @Test
    public void testProductSaved(){
        User user = new User();
        user.setUserId(1);
        Products products = new Products();
        products.setName("phone");
        products.setCategory(Category.ELECTRONICS);
        products.setUser(user);
        products.setQuantity(5);
        products.setPrice(5.0);
        Products savedProducts = productsRepository.save(products);
        Assertions.assertEquals(1,savedProducts.getProductId());
        Assertions.assertEquals("ELECTRONICS",savedProducts.getCategory().name());
    }
}
