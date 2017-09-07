package com.mediaocean.retail.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;


@Data
public class ItemBill {

    private Long productID;
    private String productName;
    private Long quantity;
    private BigDecimal rate;
    private BigDecimal taxableAmount;
    private BigDecimal salesTaxPercentage;
    private BigDecimal salesTax;
    private BigDecimal totalAmount;



}
