package com.spring.assessment.repository;

import com.spring.assessment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Darrel Rayen on 2/9/19.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductName(String productName);
}
