package com.infosys.inventoryApplication.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infosys.inventoryApplication.bean.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private ProductRepository repository;

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public String getMaxProductId() {
        return repository.getMaxProductId(); // fixed syntax
    }

    @Override
    public void removeProduct(String id) {
        repository.deleteById(id); // Correct call
    }

    @Override
    public List<Product> showAllProduct() {
        return repository.findAll(); // fixed method name
    }

    @Override
    public Double getReorderLeveByProductId(String id) {
        Product product = repository.findById(id).orElse(null);
        return product != null ? product.getReorderLevel() : null;
    }

	@Override
	public String getMaxroductId() {
		// TODO Auto-generated method stub
		return null;
	}
}
