package com.revature.revbay.transactions;

import com.revature.revbay.util.Exceptions.DataNotFoundException;
import com.revature.revbay.util.Exceptions.InvalidInputException;
import com.revature.revbay.util.Interfaces.Servicable;

import java.util.List;
import java.util.function.Predicate;

public class TransactionsService implements Servicable<Transactions> {

    // -> lamba: format () -> {}, defining any parameteres used by the function and it's execution. Parenthesis not necessary for oen parameter
    private Predicate<String> isNotEmpty = str -> str!=null && !str.isBlank();
    private TransactionsRepository transactionsRepository;

    public TransactionsService(TransactionsRepository transactionsRepository){
        this.transactionsRepository=transactionsRepository;
    }

    @Override
    public List<Transactions> findAll() {
        List<Transactions> transactions = transactionsRepository.findAll();
        if(transactions.isEmpty()){
            throw new DataNotFoundException("No Transaction Data Avaliable");
        }
        else
            return transactions;
    }

    @Override
    public Transactions create(Transactions transactions) {

        return transactionsRepository.save(transactions);
    }

    @Override
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
