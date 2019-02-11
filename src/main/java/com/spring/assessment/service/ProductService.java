package com.spring.assessment.service;

import com.spring.assessment.dto.ProductDto;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
public interface ProductService {

    ProductDto findOne(Integer id);

    Double calculatePrice(Integer productId, Integer quantity, String orderType);
}
