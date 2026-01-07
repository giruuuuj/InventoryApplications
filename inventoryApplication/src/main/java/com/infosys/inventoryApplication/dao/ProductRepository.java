package com.infosys.inventoryApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.infosys.inventoryApplication.bean.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT COALESCE(MAX(p.productId), 'P100000') FROM Product p")
    String getMaxProductId();

    @Query("SELECT p.reorderLevel FROM Product p WHERE p.productId = :id")
    Double getRecordLevelBYProductId(@Param("id") String id);
}
