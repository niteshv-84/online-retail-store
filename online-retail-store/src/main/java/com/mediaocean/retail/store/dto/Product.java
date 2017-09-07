package com.mediaocean.retail.store.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Product {

    private Long productID;
    private String productName;
    private String category;
    private BigDecimal rate;
    private BigDecimal salesTaxPercentage;
}
