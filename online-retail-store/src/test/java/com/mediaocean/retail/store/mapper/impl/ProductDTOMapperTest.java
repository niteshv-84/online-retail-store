package com.mediaocean.retail.store.mapper.impl;


import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.testutil.TestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductDTOMapperTest {

    private Product inputProduct;
    private ProductDTOMapper mapper;

    @Before
    public void setUp(){
        inputProduct = TestDataBuilder.getProduct();
        mapper = new ProductDTOMapper();
    }
    @Test
    public void mapProductDTOWhenValidInput(){
        com.mediaocean.retail.store.dto.Product product = mapper.map(inputProduct, null);
        assertNotNull(product);
        assertEquals(inputProduct.getCategory().getName(), product.getCategory());
        assertEquals(inputProduct.getId(), product.getProductID());
        assertEquals(inputProduct.getName(), product.getProductName());
        assertEquals(inputProduct.getRate(), product.getRate());
    }

    @Test
    public void mapProductDTOWhenNoCategory(){
        inputProduct.setCategory(null);
        com.mediaocean.retail.store.dto.Product product = mapper.map(inputProduct, null);
        assertNotNull(product);
        assertNull(product.getCategory());

    }

    @Test
    public void mapProductDTOWhenNullInput(){

        com.mediaocean.retail.store.dto.Product product = mapper.map(null, null);
        assertNull(product);
    }
}
