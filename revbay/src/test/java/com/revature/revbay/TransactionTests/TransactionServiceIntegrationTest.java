package com.revature.revbay.TransactionTests;


import com.revature.revbay.transactions.TransactionsRepository;
import com.revature.revbay.transactions.TransactionsService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceIntegrationTest {

    @Mock
    private TransactionsService transactionsService;

    @InjectMocks
    private TransactionsRepository transactionsRepository;

    @Test
    public void testSaveAndRetrieveTransaction(){
        //TODO Create A Transaction

        //TODO Save Created Transaction

        //TODO Retrieve the Transaction

        //TODO Verify that the retrieved Transaction is the same as the original
    }

    @Test
    public void testFindByID(){
        //TODO Implement Me
    }

    public void testUpdate(){
        //TODO Implement Me
    }
}
