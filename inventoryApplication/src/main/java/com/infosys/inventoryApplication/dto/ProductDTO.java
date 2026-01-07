package com.infosys.inventoryApplication.dto;

public class ProductDTO {
    private String productId;
    private String productName;
    private String skuId;
    private Double purchasePrice;
    private Double salesPrice;
    private Double reorderLevel;
    private Double stock;
    private String vendorId;
    private Boolean status;
    
    // Business logic calculated fields
    private String stockStatus;
    private String stockStatusClass;
    private String vendorName;

    // Constructors
    public ProductDTO() {}

    public ProductDTO(String productId, String productName, String skuId, Double purchasePrice, 
                     Double salesPrice, Double reorderLevel, Double stock, String vendorId, 
                     Boolean status, String stockStatus, String stockStatusClass, String vendorName) {
        this.productId = productId;
        this.productName = productName;
        this.skuId = skuId;
        this.purchasePrice = purchasePrice;
        this.salesPrice = salesPrice;
        this.reorderLevel = reorderLevel;
        this.stock = stock;
        this.vendorId = vendorId;
        this.status = status;
        this.stockStatus = stockStatus;
        this.stockStatusClass = stockStatusClass;
        this.vendorName = vendorName;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Double getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Double reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getStockStatusClass() {
        return stockStatusClass;
    }

    public void setStockStatusClass(String stockStatusClass) {
        this.stockStatusClass = stockStatusClass;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
