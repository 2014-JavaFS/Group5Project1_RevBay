package com.revature.revbay.transactions;


import com.revature.revbay.dtos.CartResponseDTO;
import com.revature.revbay.dtos.TransactionRequestDTO;
import com.revature.revbay.dtos.TransactionResponseDTO;
import com.revature.revbay.products.Products;
import com.revature.revbay.products.ProductsService;
import com.revature.revbay.user.User;
import com.revature.revbay.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;
    private final ProductsService productsService;
    private final UserService userService;


    public TransactionsController(TransactionsService transactionsService, UserService userService, ProductsService productsService){
        this.transactionsService=transactionsService;
        this.productsService=productsService;
        this.userService=userService;
    }

    @GetMapping
    public @ResponseBody List<Transactions> getAllTransactions(){
        return transactionsService.findAll();
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> postNewTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){

        Products products = productsService.findById(transactionRequestDTO.getSellerID());
        User user = userService.findById(transactionRequestDTO.getBuyerID());
        products.setProductId(transactionRequestDTO.getSellerID());

        Transactions transactions = new Transactions();
        transactions.setBuyerID(user);
        transactions.setSellerID(products);
        transactions.setQuantity(transactionRequestDTO.getQuantity());
        transactions.setQuantity(transactionRequestDTO.getQuantity());

        TransactionResponseDTO makingTransaction = transactionsService.createDTO(transactions);

        return ResponseEntity.status(HttpStatus.CREATED).body(makingTransaction);
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
