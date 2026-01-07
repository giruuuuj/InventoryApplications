package com.infosys.inventoryApplication.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.infosys.inventoryApplication.bean.Product;
import com.infosys.inventoryApplication.dao.ProductDao;
import com.infosys.inventoryApplication.dto.ProductDTO;
import com.infosys.inventoryApplication.service.ProductService;

@RestController
@RequestMapping("/invent/")
@CrossOrigin(origins = "http://localhost:3131", allowCredentials = "true")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductDao productDao;
    
    @GetMapping("/product")
    public List<ProductDTO> displayAllProducts() {
        List<Product> productList = productDao.showAllProduct();
        return service.convertToDTOList(productList);
    }
    
    @PostMapping("/product")
    public Product saveNewProduct(@RequestBody Product product) {
        try {
            // Generate ID if not provided
            if (product.getProductId() == null || product.getProductId().isEmpty()) {
                product.setProductId(service.productIdGenerator());
            }
            
            Product finalProduct = service.setSalesPrice(product);
            productDao.saveProduct(finalProduct);
            return finalProduct;
        } catch (Exception e) {
            System.err.println("Error saving product: " + e.getMessage());
            e.printStackTrace();
            
            // Fallback: try to save with a simple generated ID
            try {
                if (product.getProductId() == null || product.getProductId().isEmpty()) {
                    product.setProductId("P" + (100000 + (int)(Math.random() * 900000)));
                }
                Product finalProduct = service.setSalesPrice(product);
                productDao.saveProduct(finalProduct);
                return finalProduct;
            } catch (Exception fallbackError) {
                System.err.println("Fallback also failed: " + fallbackError.getMessage());
                throw new RuntimeException("Failed to save product: " + e.getMessage());
            }
        }
    }
    
    @GetMapping("/product/{id}")
    public Product showAProduct(@PathVariable String id) {
        Product product = productDao.getProductById(id);
        return product;
    }
    
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable String id) {
        productDao.removeProduct(id);
    }
    
    @PutMapping("/product/{qty}/{flag}")
    public void editProductStock(@RequestBody Product product, @PathVariable double qty, @PathVariable boolean flag) {
        Product updatedProduct = service.stockEdit(qty, flag, product);
        productDao.saveProduct(updatedProduct);
    }
    
    @GetMapping("/calculate-sales-price/{purchasePrice}")
    public Map<String, Object> calculateSalesPrice(@PathVariable double purchasePrice) {
        Map<String, Object> response = new HashMap<>();
        double salesPrice = purchasePrice * 1.20; // 20% markup
        response.put("purchasePrice", purchasePrice);
        response.put("salesPrice", salesPrice);
        response.put("markupPercentage", 20.0);
        response.put("message", "Sales price calculated with 20% markup");
        return response;
    }

    @PutMapping("/product")
    public void editProduct(@RequestBody Product product) {
        Product updatedProduct = service.setSalesPrice(product);
        productDao.saveProduct(updatedProduct);
    }
    
    @GetMapping("/id-gen")
    public String productIdGenerator() {
        return service.productIdGenerator();
    }
}