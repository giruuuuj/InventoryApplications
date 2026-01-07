package com.infosys.inventoryApplication.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infosys.inventoryApplication.bean.ProductSale;
import com.infosys.inventoryApplication.bean.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private TransactionRepository repository;

    @Override
    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }
    
    @Override
    public Transaction findTransactionById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    @Override
    public Long findMaxTransactionId() {
        return repository.findMaxTransactionId();
    }
    
    @Override
    public List<Transaction> showAllTransaction() {
        return repository.findAll();
    }
    
    @Override
    public List<Transaction> findAllTransactionsByType(String type) {
        return repository.findTransactionByType(type);
    }
    
    @Override
    public void removeTransactionById(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public List<Double> getDemandByProduct(String productId) {
        return repository.getDemandByProduct(productId);
    }
    @Override
	public List<ProductSale> getProductWiseTotalSale() {
		return repository.getProductWiseTotalSale();
	}
}