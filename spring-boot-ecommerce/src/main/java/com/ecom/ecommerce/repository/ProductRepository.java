package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "product", path = "products")
public interface ProductRepository  extends JpaRepository<Product,Long> {
}
