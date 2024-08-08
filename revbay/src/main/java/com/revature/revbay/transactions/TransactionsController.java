package com.revature.revbay.transactions;


import com.fasterxml.jackson.databind.JsonNode;
import com.revature.revbay.cart.Cart;
import com.revature.revbay.cart.CartService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;
    private final ProductsService productsService;
    private final UserService userService;
    private final CartService cartService;


    public TransactionsController(TransactionsService transactionsService, UserService userService, ProductsService productsService, CartService cartService){
        this.transactionsService=transactionsService;
        this.productsService=productsService;
        this.userService=userService;
        this.cartService=cartService;

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
        System.out.println(transactions.getQuantity());

        TransactionResponseDTO makingTransaction = transactionsService.createDTO(transactions);
        transactions.setTotalPrice(makingTransaction.getTotalPrice());
        transactionsService.update(transactions);

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


    @PostMapping("/History")
    private ResponseEntity<List<TransactionResponseDTO>> getAccountHistory(@RequestBody JsonNode json){
        int userId= json.asInt();
        return ResponseEntity.status(200).body(transactionsService.getHistory(userId));
        //return ResponseEntity.ok(transactionsService.getHistory(userId));
    }


    @PostMapping("/Checkout")
    private @ResponseBody List<TransactionResponseDTO> postCheckout(@RequestBody Integer userId){

        //TODO Get a List of Everything In CHeckout
        List<TransactionResponseDTO> userReturn = new ArrayList<>();

        List<Cart> toSort = cartService.findAll();
        List<Cart> sorted = new ArrayList<>();
        for(int i=0;i<toSort.size();i++){
            if(toSort.get(i).getUser().getUserId()==userId){
                sorted.add(toSort.get(i));
            }
        }

        for(int i=0;i<sorted.size();i++){
            //Creating the Transaction Fields to Send to make A DTO
            Transactions toDTO = new Transactions();
            Products product = productsService.findById(sorted.get(i).getProducts().getProductId());
            User user = userService.findById(userId);
            int quantity = sorted.get(i).getQuantity();
            //Populating the Transaction
            toDTO.setQuantity(quantity);
            toDTO.setBuyerID(user);
            toDTO.setSellerID(product);

            //Adding to User Return
            userReturn.add(transactionsService.createDTO(toDTO));
            //Deleting the Entry from Cart Repository
            cartService.delete(sorted.get(i).getActiveCartItem());
        }

        return userReturn;
    }

}
