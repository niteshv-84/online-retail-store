package com.mediaocean.retail.store.testutil;

import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.dto.Item;
import com.mediaocean.retail.store.dto.ItemBill;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.model.ProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    private final static BigDecimal HUNDRED = new BigDecimal(100);
    private final static BigDecimal TWENTY = new BigDecimal(20);

    public static Product getProduct() {
        Product product = new Product();
        product.setId(new Long(1));
        product.setName("Oil");
        product.setDescription("Basic Refined Oil");
        product.setRate(HUNDRED);
        product.setCategory(getCategoryA());
        return product;
    }

    public static Product getAnotherProduct() {
        Product product = new Product();
        product.setId(new Long(2));
        product.setName("Biscuit");
        product.setDescription("Basic Refined Oil");
        product.setRate(HUNDRED);
        product.setCategory(getCategoryB());
        return product;
    }

    public static ProductCategory getCategoryA() {
        ProductCategory category = new ProductCategory();
        category.setId(1L);
        category.setName("A");
        category.setDescription("Category A Products");
        category.setTaxPercentage(BigDecimal.TEN);
        return category;
    }

    public static ProductCategory getCategoryB() {
        ProductCategory category = new ProductCategory();
        category.setId(2L);
        category.setName("B");
        category.setDescription("Category B Products");
        category.setTaxPercentage(TWENTY);
        return category;
    }

    public static Item getItem(){
        Item item = new Item();
        item.setProductID(1L);
        item.setQuantity(5L);
        return item;
    }

    public static Item getAnotherItem(){
        Item item = new Item();
        item.setProductID(2L);
        item.setQuantity(7L);
        return item;
    }

    public static com.mediaocean.retail.store.dto.Product getDtoProduct(){
        com.mediaocean.retail.store.dto.Product product = new com.mediaocean.retail.store.dto.Product();
        product.setCategory("A");
        product.setProductID(1L);
        product.setProductName("Oil");
        product.setRate(HUNDRED);
        product.setSalesTaxPercentage(BigDecimal.TEN);

        return product;
    }

    public static Cart getCart(){
       Cart cart = new Cart();
       List<Item> itemList = new ArrayList<>();
       itemList.add(getItem());
        itemList.add(getAnotherItem());
       cart.setItemList(itemList);
       return cart;
    }

    public static ItemBill getItemBill(){
        ItemBill itemBill = new ItemBill();
        itemBill.setProductID(1L);
        itemBill.setProductName("Oil");
        itemBill.setQuantity(5L);
        itemBill.setSalesTaxPercentage(BigDecimal.TEN);
        itemBill.setRate(HUNDRED);
        return itemBill;
    }

    public static ItemBill getAnotherItemBill(){
        ItemBill itemBill = new ItemBill();
        itemBill.setProductID(2L);
        itemBill.setProductName("Biscuit");
        itemBill.setQuantity(7L);
        itemBill.setSalesTaxPercentage(TWENTY);
        itemBill.setRate(HUNDRED);
        return itemBill;
    }
}
