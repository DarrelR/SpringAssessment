package com.spring.assessment.dto;

import com.spring.assessment.model.Product;
import lombok.Data;

/**
 * Created by Darrel Rayen on 2/9/19.
 */
@Data
public class ProductDto {

    private Integer id;

    private String productName;

    private Double boxPrice;

    private Integer unitsPerBox;

    private Double unitPrice;


    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.boxPrice = product.getBoxPrice();
        this.unitsPerBox = product.getUnitsPerBox();
        this.unitPrice = product.getUnitPrice();
    }
}
