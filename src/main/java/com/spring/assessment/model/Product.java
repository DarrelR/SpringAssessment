package com.spring.assessment.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Darrel Rayen on 2/11/19.
 */
@Entity
@Data
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String productName;

    private Double boxPrice;

    private Integer unitsPerBox;

    private Double unitPrice;

    public Product() {
    }
}
