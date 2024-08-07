package com.revature.revbay.TransactionTests;

import com.revature.revbay.products.Products;
import com.revature.revbay.transactions.Transactions;
import com.revature.revbay.transactions.TransactionsRepository;
import com.revature.revbay.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionRepositoryIntegrationTesting {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void testTransactionSaving(){

        //Creating a new User
        User user = new User();
        user.setUserId(5);
        //Creating a new Transaction
        Products products = new Products();
        products.setProductId(5);
        products.setName("Test");
        products.setPrice(9.99);

        Transactions savedTransaction = new Transactions();
        savedTransaction.setBuyerID(user);
        savedTransaction.setSellerID(products);
        savedTransaction.setDestination("Test Location");
        savedTransaction.setTotalPrice((new BigDecimal(69.69)));
        transactionsRepository.save(savedTransaction);
        Assertions.assertEquals(1,transactionsRepository.getReferenceById(1).getTransactionID());
        //Assertions.assertEquals("Test Location", savedTransaction.getDestination());
        //Assertions.assertEquals(5,savedTransaction.getBuyerID().getUserId());

    }



}
