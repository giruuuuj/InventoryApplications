package com.infosys.inventoryApplication.bean;

public class ProductSale{
	private String productName;
	private Double totalSales;
	public ProductSale() {
		
		super();
		
	}
	public ProductSale(String productName, Double totalSales) {
        super();
        this.productName = productName;
        this.totalSales = totalSales;
    }
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
        this.productName = productName;
    }
	public Double getTotalSaleValue() {
		return totalSales;
	}
	public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

	public void setTotalSaleValue(Double totalSales) {
		this.totalSales = totalSales;
	}
}