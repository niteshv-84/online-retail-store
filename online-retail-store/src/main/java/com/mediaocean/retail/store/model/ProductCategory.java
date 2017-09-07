package com.mediaocean.retail.store.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Table(name="PRODUCT_CATEGORY")
@Entity
public class ProductCategory {

 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_CATEGORY_SEQ")
 @SequenceGenerator(sequenceName = "product_category_seq", allocationSize = 1, name = "PRODUCT_CATEGORY_SEQ")
 private Long id;
 private String name;
 private String description;
 @Column(name = "TAX_PERCENTAGE")
 private BigDecimal taxPercentage;

}
