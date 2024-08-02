package com.revature.revbay.TransactionTests;

import com.revature.revbay.transactions.Transactions;
import com.revature.revbay.transactions.TransactionsRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@DataJpaTest
@ActiveProfiles("test")
public class TransactionRepositoryIntegrationTesting {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void testTransactionSaving(){

    }



}
