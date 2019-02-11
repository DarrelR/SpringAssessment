package com.spring.assessment.controller;

import com.spring.assessment.model.Product;
import com.spring.assessment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Darrel Rayen on 2/9/19.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This returns product list view
     *
     * @param modelAndView - parameter accepts the html page details & the dto objects displayed in the front rnd
     * @param page - accepts the page number
     * @return ModelView object
     */
    @GetMapping
    public ModelAndView getProductsList(ModelAndView modelAndView, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        modelAndView.setViewName("/product-list");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("actionUrl", "/products?");
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("productList", productService.findAllProducts(PageRequest.of(page - 1, 50)));
        return modelAndView;
    }
}
