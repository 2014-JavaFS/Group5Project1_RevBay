package com.revature.revbay.transactions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;


    public TransactionsController(TransactionsService transactionsService){
        this.transactionsService=transactionsService;
    }

    @GetMapping
    public @ResponseBody List<Transactions> getAllTransactions(){
        return transactionsService.findAll();
    }

    @PostMapping
    public ResponseEntity<Transactions> postNewTransaction(@RequestBody Transactions transactions, @RequestHeader String userType){

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionsService.create(transactions));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Transactions> getTransactionById(@PathVariable int id){

        return ResponseEntity.ok(transactionsService.findById(id));
    }

    @PutMapping
    private ResponseEntity<Boolean> putUpdateTransaction(@RequestBody Transactions updatedTransaction){
        return ResponseEntity.ok(transactionsService.update(updatedTransaction));
    }

}
