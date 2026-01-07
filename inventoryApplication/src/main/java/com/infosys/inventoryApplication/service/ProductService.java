package com.infosys.inventoryApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.inventoryApplication.bean.Product;
import com.infosys.inventoryApplication.dao.ProductRepository;
import com.infosys.inventoryApplication.dto.ProductDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Update stock based on qty and flag (flag: true=add, false=subtract)
    public Product stockEdit(double qty, boolean flag, Product product) {
        
        double stock = product.getStock();
        
        if (flag) {
            stock += qty;  // add stock when flag is true
        } else if (stock >= qty) {
            stock -= qty;  // subtract stock when flag is false
        } else {
            throw new RuntimeException("Invalid operation: Not enough stock!");
        }

        product.setStock(stock);
        productRepository.save(product);

        return product;  // return updated product
    }

    // Alternative method with different parameter order
    public Product stockEdit(Product product, double qty, boolean flag) {
        return stockEdit(qty, flag, product);
    }

    // Auto-generate product ID
    public String productIdGenerator() {
        try {
            String id = productRepository.getMaxProductId();
            System.out.println("Max Product ID from DB: " + id);

            if (id == null || id.trim().isEmpty()) {
                id = "P100001";
            } else {
                // Extract numeric part and increment
                String numericPart = id.replaceAll("[^0-9]", "");
                if (numericPart.isEmpty()) {
                    id = "P100001";
                } else {
                    int x = Integer.parseInt(numericPart);
                    x++;
                    id = "P" + x;
                }
            }
            
            System.out.println("Generated Product ID: " + id);
            return id;
        } catch (Exception e) {
            System.err.println("Error generating Product ID: " + e.getMessage());
            return "P100001";
        }
    }

    public Product setSalesPrice(Product product) {
        // Calculate sales price as 20% above purchase price
        double purchasePrice = product.getPurchasePrice();
        double salesPrice = purchasePrice * 1.20; // 20% markup
        product.setSalesPrice(salesPrice);
        
        // Calculate status based on stock and reorder level
        double stock = product.getStock() != null ? product.getStock() : 0;
        double reorderLevel = product.getReorderLevel() != null ? product.getReorderLevel() : 0;
        boolean status = stock > reorderLevel;
        product.setStatus(status);
        
        return product;
    }

    // Convert Product entity to ProductDTO with business logic
    public ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setSkuId(product.getSkuId());
        dto.setPurchasePrice(product.getPurchasePrice());
        dto.setSalesPrice(product.getSalesPrice());
        dto.setReorderLevel(product.getReorderLevel());
        dto.setStock(product.getStock());
        dto.setVendorId(product.getVendorId());
        dto.setStatus(product.getStatus());
        
        // Apply business logic
        dto.setStockStatus(calculateStockStatus(product.getStock(), product.getReorderLevel()));
        dto.setStockStatusClass(calculateStockStatusClass(product.getStock(), product.getReorderLevel()));
        dto.setVendorName(formatVendorName(product.getVendorId()));
        
        return dto;
    }

    // Convert list of Products to list of ProductDTOs
    public List<ProductDTO> convertToDTOList(List<Product> products) {
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Business logic methods
    private String calculateStockStatus(Double stock, Double reorderLevel) {
        if (stock == null || reorderLevel == null) {
            return "Unknown";
        }
        return stock > reorderLevel ? "Permitted to Issue" : "Reorder Level Reached";
    }

    private String calculateStockStatusClass(Double stock, Double reorderLevel) {
        if (stock == null || reorderLevel == null) {
            return "text-warning";
        }
        return stock > reorderLevel ? "text-success" : "text-danger";
    }

    private String formatVendorName(String vendorId) {
        if (vendorId == null) {
            return "Unknown";
        }
        
        // If vendorId is in format "vendor-X", extract the number
        if (vendorId.startsWith("vendor-")) {
            String vendorNum = vendorId.replace("vendor-", "");
            return "Vendor " + vendorNum;
        }
        
        // Otherwise return the vendorId as is
        return vendorId;
    }
}