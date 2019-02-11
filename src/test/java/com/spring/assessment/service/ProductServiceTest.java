package com.spring.assessment.service;

import com.spring.assessment.dto.ProductDto;
import com.spring.assessment.model.Product;
import com.spring.assessment.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void findProductByName() {
        Product product = new Product();
        product.setProductName("Penguinears");
        given(productRepository.findByProductName("Penguinears")).willReturn(product);

        ProductDto product1 = productService.findByName("Penguinears");
        assertTrue(product1.getProductName().equals("Penguinears"));
    }

}