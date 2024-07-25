package com.revature.revbay.products;

import com.revature.revbay.util.enums.Category;
import com.revature.revbay.util.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }
    public List<Products> findAll(){
        List<Products> products = productsRepository.findAll();
        if(products.isEmpty()){
            throw new DataNotFoundException("No product information found");
        }else {
            return products;
        }
    }
    public Products create(Products products) {
        products.setCategory(Category.valueOf("GENERAL"));
        return productsRepository.save(products);
    }
    public Products findById(int id){
        Optional<Products> products = productsRepository.findById(id);
        if(products.isEmpty()){
            throw new DataNotFoundException("Product information by id not found");
        }else {
            return products.get();
        }
    }
    public boolean delete(Products products){
        productsRepository.delete(products);
        return true;
    }
    @Transactional
    public boolean update(Products products){
        Products foundProducts = productsRepository.findById(products.getProductId()).orElseThrow(()->new DataNotFoundException("No product with that id number"));
        if(foundProducts==null){
            throw new DataNotFoundException("Product with id not in database");
        }
        productsRepository.saveAndFlush(products);
        return true;
    }

}
