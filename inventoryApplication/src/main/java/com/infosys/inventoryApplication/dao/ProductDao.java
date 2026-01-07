package com.infosys.inventoryApplication.dao;

import java.util.List;
import com.infosys.inventoryApplication.bean.Product;

public interface ProductDao {

   public void saveProduct(Product product);
   public Product getProductById(String id);
   public String getMaxroductId();
   public void removeProduct(String id );
   public List<Product> showAllProduct();
   public Double getReorderLeveByProductId(String id);
   public String getMaxProductId();
   
   
   
   
}
