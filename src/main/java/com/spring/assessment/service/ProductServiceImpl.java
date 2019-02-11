package com.spring.assessment.service;

import com.spring.assessment.dto.ProductDto;
import com.spring.assessment.model.OrderType;
import com.spring.assessment.model.Product;
import com.spring.assessment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Darrel Rayen on 2/9/19.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProducts(Pageable pageable) {
        Page<Product> productPageList = productRepository.findAll(pageable);
        return productPageList.map(ProductDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findOne(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductDto::new).orElse(null);
    }

    @Override
    @Transactional
    public Double calculatePrice(Integer productId, Integer quantity, String orderType) {
        Double totalPrice = 0.0;
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            Product product = optional.get();
            if (orderType.equalsIgnoreCase(OrderType.BOX.toString())) {
                if (quantity >= 3) {
                    totalPrice = (product.getBoxPrice() * 90 / 100) * quantity;
                } else {
                    totalPrice = product.getBoxPrice() * quantity;
                }
            } else if (orderType.equalsIgnoreCase(OrderType.ITEM.toString())) {

                Integer totalItemsInBox = product.getUnitsPerBox();
                if (quantity < totalItemsInBox) {
                    totalPrice = product.getUnitPrice() * quantity;
                } else {
                    Integer boxCount = quantity / totalItemsInBox;
                    Integer itemCount = quantity % totalItemsInBox;
                    if (boxCount >= 3) {
                        totalPrice = (product.getBoxPrice() * 90 / 100) * boxCount;
                    } else {
                        totalPrice = product.getBoxPrice() * boxCount;
                    }
                    totalPrice += itemCount * product.getUnitPrice();
                }
            }
        } else {
            return null;
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public ProductDto addProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setProductName(product.getProductName());
        newProduct.setBoxPrice(product.getBoxPrice());
        newProduct.setUnitsPerBox(product.getUnitsPerBox());
        Double actualUnitPrice = product.getBoxPrice() / product.getUnitPrice();
        Double unitPrice = actualUnitPrice + (30 / 100 * actualUnitPrice);
        newProduct.setUnitPrice(unitPrice);
        return new ProductDto(productRepository.saveAndFlush(newProduct));
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findByName(String productName) {
        Product product = productRepository.findByProductName(productName);
        return product != null ? new ProductDto(product) : null;
    }
}
