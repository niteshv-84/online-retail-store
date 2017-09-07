package com.mediaocean.retail.store.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    @SequenceGenerator(sequenceName = "product_seq", allocationSize = 1, name = "PRODUCT_SEQ")
    private Long id;
    private String name;
    private String description;
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory category;



}
