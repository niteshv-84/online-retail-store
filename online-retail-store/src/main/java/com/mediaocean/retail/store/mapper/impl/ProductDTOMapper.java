package com.mediaocean.retail.store.mapper.impl;

import com.mediaocean.retail.store.dto.Product;
import com.mediaocean.retail.store.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component("ProductDTOMapper")
public class ProductDTOMapper implements Mapper<com.mediaocean.retail.store.model.Product, Product> {

    @Override
    public Product map(com.mediaocean.retail.store.model.Product product, Object... otherInputs) {
        if(product == null) {
            return null;
        }
        Product dtoProduct = new Product();
        dtoProduct.setProductID(product.getId());
        dtoProduct.setProductName(product.getName());
        dtoProduct.setRate(product.getRate());
        if (product.getCategory() != null) {
            dtoProduct.setCategory(product.getCategory().getName());
            dtoProduct.setSalesTaxPercentage(product.getCategory().getTaxPercentage());
        }
        return dtoProduct;
    }
}