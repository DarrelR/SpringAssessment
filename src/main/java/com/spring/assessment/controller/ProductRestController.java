package com.spring.assessment.controller;

import com.spring.assessment.dto.ProductDto;
import com.spring.assessment.exception.ProductNotFoundException;
import com.spring.assessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {


    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "order/{id}", method = RequestMethod.GET)
    public ResponseEntity<Double> addOrder(@PathVariable("id") Integer id, @RequestParam("quantity") Integer quantity,
                                           @RequestParam("orderType") String orderType) {
        Double totalPrice = productService.calculatePrice(id, quantity, orderType);
        if (totalPrice != null) {
            return new ResponseEntity<>(totalPrice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Integer id) {
        ProductDto productDto = productService.findOne(id);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            throw new ProductNotFoundException();
        }
    }
}
