package com.infosys.inventoryApplication.dao;

import java.util.List;
import com.infosys.inventoryApplication.bean.Transaction;

public interface TransactionDao {
    public void saveTransaction(Transaction transaction);
    public Transaction findTransactionById(Long id);
    public Long findMaxTransactionId();
    public List<Transaction> findAllTransactionsByType(String type); 
    public List<Transaction> showAllTransaction();
    public void removeTransactionById(Long id);
    public List<Double> getDemandByProduct(String productId);
}