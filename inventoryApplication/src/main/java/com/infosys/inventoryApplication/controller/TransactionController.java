package com.infosys.inventoryApplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.infosys.inventoryApplication.bean.ProductSale;
import com.infosys.inventoryApplication.bean.Transaction;
import com.infosys.inventoryApplication.dao.TransactionDao;
import com.infosys.inventoryApplication.service.TransactionService;

@RestController
@RequestMapping("/invent")
@CrossOrigin(origins = "http://localhost:3131", allowCredentials = "true")
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionService service;

    // Save transaction
    @PostMapping("/stock")
    public void saveTransaction( @RequestBody Transaction transaction) {
        transactionDao.saveTransaction(transaction);
    }

    // Get transaction by ID
    @GetMapping("/stock/{id}")
    public Transaction findTransactionById(@PathVariable Long id) {
        return transactionDao.findTransactionById(id);
    }

    // Get all transactions
    @GetMapping("/stock")
    public List<Transaction> showAllTransaction() {
        return transactionDao.showAllTransaction();
    }

    // Delete transaction
    @DeleteMapping("/stock/{id}")
    public void removeTransactionById(@PathVariable Long id) {
        transactionDao.removeTransactionById(id);
    }

    // Generate transaction ID
    @GetMapping("/trans")
    public Long generateId() {
        return service.generateId();
    }

    // Save transaction (alternative endpoint)
    @PostMapping("/trans")
    public void saveTransactionTrans(@RequestBody Transaction transaction) {
        transactionDao.saveTransaction(transaction);
    }

    // Get transactions by type
    @GetMapping("/trans/type/{type}")
    public List<Transaction> findAllTransactionsByType(@PathVariable String type) {
        return transactionDao.findAllTransactionsByType(type);
    }

    // Demand analysis
    @GetMapping("/analysis/{id}")
    public List<Double> getDemandByProduct(@PathVariable String id) {
        return transactionDao.getDemandByProduct(id);
    }
    @GetMapping("/analysis")
    public List<ProductSale>  getProductWiseTotalSale(){
    	return transactionDao.getProductWiseTotalSale();
    }
}
