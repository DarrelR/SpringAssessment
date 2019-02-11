package com.spring.assessment.service;

import com.spring.assessment.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
public interface ProductService {

    Page<ProductDto> findAllProducts(Pageable pageable);

    ProductDto findOne(Integer id);

    ProductDto findByName(String productName);

    Double calculatePrice(Integer productId, Integer quantity, String orderType);

    ProductDto addProduct(ProductDto product);
}
