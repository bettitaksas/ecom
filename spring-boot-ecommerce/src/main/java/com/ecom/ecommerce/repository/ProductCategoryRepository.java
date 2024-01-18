package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Long> {
}
