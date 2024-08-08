package com.revature.revbay.TransactionTests;


import com.revature.revbay.products.Products;
import com.revature.revbay.transactions.Transactions;
import com.revature.revbay.transactions.TransactionsRepository;
import com.revature.revbay.transactions.TransactionsService;
import com.revature.revbay.user.User;
import com.revature.revbay.util.enums.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceIntegrationTest {

    @Mock
    private TransactionsService  fakeTransactionsService;

    @InjectMocks
    private TransactionsRepository transactionsRepository;



    @Test
    public void testSaveAndRetrieveTransaction(){
        Transactions mockTransactions = null;

        //test for null inputs
        try{
            fakeTransactionsService.create(mockTransactions);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            System.out.println("Testing for which Exception gets thrown e");
        }catch(NullPointerException n){
            n.printStackTrace();
            System.out.println("Testing for which Exception gets thrown n");
        }

        User mockUser = new User(5,"Test Name-F", "Test Name-L", "Test Email", "Test Password", User.UserType.BUYER);
        Products mockProduct = new Products(5, "Test Name-F", Category.GENERAL, mockUser, 9999, 4.99);
        mockTransactions=new Transactions(696969,mockProduct,mockUser,2,new BigDecimal(89.75),"Test Location");
        transactionsRepository.save(mockTransactions);
        Transactions fromSavedDataTransaction = transactionsRepository.getReferenceById(696969);
        //TODO Possibly replace this block with some assert stuff

        if(
                fromSavedDataTransaction.getTransactionID() == mockTransactions.getTransactionID()
                &&
                        fromSavedDataTransaction.getSellerID() == mockTransactions.getSellerID()
                &&
                        fromSavedDataTransaction.getBuyerID() == mockTransactions.getBuyerID()
                &&
                        fromSavedDataTransaction.getQuantity() == mockTransactions.getQuantity()
                &&
                        Objects.equals(fromSavedDataTransaction.getTotalPrice(), mockTransactions.getTotalPrice())
                &&
                        Objects.equals(fromSavedDataTransaction.getDestination(), mockTransactions.getDestination())
        ){
            System.out.println("This should work and be Equals, " +
                    "probably should have been using Assertions and Equals though");
        }
        else {
            System.out.println("Looks like the save and Retrieved didn't work correctly");
        }


    }

    @Test
    public void testFindByID(){
        Transactions mockTransaction= new Transactions();
        try {
            mockTransaction = transactionsRepository.findById(1).get();

        }catch(NoSuchElementException e){
            e.printStackTrace();
        }

        assertEquals(1, mockTransaction.getTransactionID());
    }

    public void testUpdate() {
        //admin command

        User mockUser = new User(5,"Test Name-F", "Test Name-L", "Test Email", "Test Password", User.UserType.BUYER);
        Products mockProduct = new Products(5, "Test Name-F", Category.GENERAL, mockUser, 9999, 4.99);
        Transactions testUpdateTransaction = new Transactions(696969,mockProduct,mockUser,2,new BigDecimal(89.75),"Test Location");
        transactionsRepository.save(testUpdateTransaction);
        testUpdateTransaction.setQuantity(1000);
        fakeTransactionsService.update(testUpdateTransaction);
        testUpdateTransaction.setQuantity(100);
        testUpdateTransaction= transactionsRepository.findById(696969).get();
        assertEquals(1000, testUpdateTransaction.getQuantity());

    }

}
