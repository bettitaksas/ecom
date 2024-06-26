package com.ecom.ecommerce.populator;

import com.ecom.ecommerce.entity.Product;
import com.ecom.ecommerce.entity.ProductCategory;
import com.ecom.ecommerce.repository.ProductCategoryRepository;
import com.ecom.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataPopulator implements ApplicationListener<ContextRefreshedEvent> {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public DataPopulator(ProductRepository productRepository,
                         ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (isDatabaseEmpty()) {
            DataPopulator dataPopulator = event.getApplicationContext().getBean(DataPopulator.class);
            dataPopulator.populateData();
        }
    }

    public void populateData() {
        ProductCategory shoes = new ProductCategory();
        shoes.setCategoryName("Shoes");
        ProductCategory jackets = new ProductCategory();
        jackets.setCategoryName("Jackets");
        ProductCategory climberEquipments = new ProductCategory();
        climberEquipments.setCategoryName("Climbing");
        ProductCategory campingEquipments = new ProductCategory();
        campingEquipments.setCategoryName("Camping");

        productCategoryRepository.saveAll(
                Arrays.asList(
                        shoes,
                        jackets,
                        climberEquipments,
                        campingEquipments));

        Product shoes1 = new Product();
        shoes1.setCategory(shoes);
        shoes1.setName("shoes1");
        shoes1.setUnitPrice(BigDecimal.valueOf(49.99));
        shoes1.setImageUrl("assets/images/products/shoes1.jpg");
        shoes1.setCategory(shoes);

        Product jacket1 = new Product();
        jacket1.setCategory(jackets);
        jacket1.setName("jacket1");
        jacket1.setUnitPrice(BigDecimal.valueOf(59.99));
        jacket1.setImageUrl("assets/images/products/jacket1.jpg");
        jacket1.setCategory(jackets);

        Product climberEquipment1 = new Product();
        climberEquipment1.setCategory(climberEquipments);
        climberEquipment1.setName("climberEquipment1");
        climberEquipment1.setUnitPrice(BigDecimal.valueOf(39.99));
        climberEquipment1.setImageUrl("assets/images/products/climberEquipment1.jpg");
        climberEquipment1.setCategory(climberEquipments);

        Product campingEquipment1 = new Product();
        campingEquipment1.setCategory(campingEquipments);
        campingEquipment1.setName("campingEquipment1");
        campingEquipment1.setUnitPrice(BigDecimal.valueOf(29.99));
        campingEquipment1.setImageUrl("assets/images/products/campingEquipment1.jpg");
        campingEquipment1.setCategory(campingEquipments);


        productRepository.saveAll(
                Arrays.asList(
                        shoes1,
                        jacket1,
                        climberEquipment1,
                        campingEquipment1
                ));
    }

    private boolean isDatabaseEmpty() {
        return productRepository.count() == 0;
    }
}
