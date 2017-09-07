package com.mediaocean.retail.store.service;

import com.mediaocean.retail.store.dto.Bill;
import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.mapper.impl.ItemBillDTOMapper;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.testutil.TestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BillingServiceTest {

    private  ProductService productService;
    private  ItemBillDTOMapper itemBillDTOMapper;
    private  BillingService service;

    @Before
    public void setUp(){
        productService = mock(ProductService.class);
        itemBillDTOMapper = mock(ItemBillDTOMapper.class);
        service = new BillingService(productService,itemBillDTOMapper);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateBillWhenNullCart(){
        service.generateBill(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateBillWhenNoItems(){
        Cart cart = new Cart();
        service.generateBill(cart);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateBillWhenItemHasNoProduct(){
        Cart cart = TestDataBuilder.getCart();
        cart.getItemList().get(0).setProductID(null);
        service.generateBill(cart);
    }

    @Test
    public void generateBillForValidInput(){
        Cart cart = TestDataBuilder.getCart();

        Product categoryAProduct = TestDataBuilder.getProduct();
        Product categoryBProduct = TestDataBuilder.getAnotherProduct();
        List<Product> products = new ArrayList<>();
        products.add(categoryAProduct);
        products.add(categoryBProduct);

        when(productService.getProductsByIds(any())).thenReturn(products);
        when(itemBillDTOMapper.map(cart.getItemList().get(0),categoryAProduct)).thenReturn(TestDataBuilder.getItemBill());
        when(itemBillDTOMapper.map(cart.getItemList().get(1),categoryBProduct)).thenReturn(TestDataBuilder.getAnotherItemBill());

       Bill bill = service.generateBill(cart);
       assertNotNull(bill);
        assertEquals(new BigDecimal(1200),bill.getTaxableAmount());
       assertEquals(new BigDecimal(190),bill.getSalesTax());
        assertEquals(new BigDecimal(1390),bill.getTotalAmount());
    }
}
