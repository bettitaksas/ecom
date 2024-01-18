package com.ecom.ecommerce.populator;

import com.ecom.ecommerce.entity.Product;
import com.ecom.ecommerce.entity.ProductCategory;
import com.ecom.ecommerce.repository.ProductCategoryRepository;
import com.ecom.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataPopulator implements ApplicationRunner {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final EntityManager entityManager;

    @Autowired
    public DataPopulator(ProductRepository productRepository,
                         ProductCategoryRepository productCategoryRepository,
                         EntityManager entityManager) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        populateData();
    }

    public void populateData(){
        ProductCategory shoes = new ProductCategory();
        ProductCategory jackets = new ProductCategory();
        ProductCategory climberEquipments = new ProductCategory();
        ProductCategory campingEquipments = new ProductCategory();

        productCategoryRepository.saveAll(
                Arrays.asList(
                        shoes,
                        jackets,
                        climberEquipments,
                        campingEquipments));

        Product shoes1 = new Product();
        shoes1.setCategory(shoes);
        shoes1.setName("shoes1");

        Product jacket1 = new Product();
        jacket1.setCategory(jackets);
        jacket1.setName("jacket1");

        Product climberEquipment1 = new Product();
        climberEquipment1.setCategory(climberEquipments);
        climberEquipment1.setName("climberEquipment1");

        Product campingEquipment1 = new Product();
        campingEquipment1.setCategory(campingEquipments);
        campingEquipment1.setName("campingEquipment1");

        productRepository.saveAll(
                Arrays.asList(
                        shoes1,
                        jacket1,
                        climberEquipment1,
                        campingEquipment1
                ));
    }
}