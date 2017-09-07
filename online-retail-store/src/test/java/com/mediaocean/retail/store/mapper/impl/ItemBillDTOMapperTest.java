package com.mediaocean.retail.store.mapper.impl;


import com.mediaocean.retail.store.dto.Item;
import com.mediaocean.retail.store.dto.ItemBill;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.testutil.TestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemBillDTOMapperTest {

    private Product inputProduct ;
    private Item item;
    private ItemBillDTOMapper mapper;

    @Before
    public void setUp(){
        inputProduct = TestDataBuilder.getProduct();
        item = TestDataBuilder.getItem();
        mapper = new ItemBillDTOMapper();
    }

    @Test
    public void mapItemBillWhenValidInput(){

        ItemBill itemBill = mapper.map(item, inputProduct);
        assertNotNull(itemBill);
        assertEquals(inputProduct.getRate() , itemBill.getRate());
        assertEquals(inputProduct.getCategory().getTaxPercentage() , itemBill.getSalesTaxPercentage());
        assertEquals(inputProduct.getName() , itemBill.getProductName());
        assertEquals(item.getQuantity() , itemBill.getQuantity());
    }

    @Test
    public void mapItemBillWhenNullProduct(){

        ItemBill itemBill = mapper.map(item, null);
        assertNotNull(itemBill);
        assertNull(itemBill.getRate());
        assertNull(itemBill.getSalesTaxPercentage());
        assertNull(itemBill.getProductName());
    }

    @Test
    public void mapItemBillWhenNullItem(){

        ItemBill itemBill = mapper.map(null, inputProduct);
        assertNull(itemBill);
    }
}
