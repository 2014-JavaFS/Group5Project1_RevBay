package com.revature.revbay.transactions;

import com.revature.revbay.util.exceptions.DataNotFoundException;
import com.revature.revbay.util.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
@Service
public class TransactionsService  {

    // -> lamba: format () -> {}, defining any parameteres used by the function and it's execution. Parenthesis not necessary for oen parameter
    private Predicate<String> isNotEmpty = str -> str!=null && !str.isBlank();
    private final TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository){
        this.transactionsRepository=transactionsRepository;
    }


    public List<Transactions> findAll() {
        List<Transactions> transactions = transactionsRepository.findAll();
        if(transactions.isEmpty()){
            throw new DataNotFoundException("No Transaction Data Avaliable");
        }
        else
            return transactions;
    }


    public Transactions create(Transactions transactions) {

        return transactionsRepository.save(transactions);
    }


    public Transactions findById(int number) throws DataNotFoundException {
        Transactions foundTransaction = transactionsRepository.findById(number)
                .orElseThrow(()-> new DataNotFoundException("Coudn't find a Transaction with the ID of +" + number));
        return foundTransaction;
    }

    public Boolean update(Transactions updatedTransaction) throws InvalidInputException {


        Transactions foundTransaction = transactionsRepository.findById(updatedTransaction.getTransactionID())
                .orElseThrow(()-> new DataNotFoundException("Coudn't find a Transaction with the ID of +" + updatedTransaction.getTransactionID()));
        if(foundTransaction==null){
            throw new DataNotFoundException("Transaction with that ID not in the Database, Please Check Physical Records?");
        }
        transactionsRepository.saveAndFlush(updatedTransaction);
        return true;
    }


}
