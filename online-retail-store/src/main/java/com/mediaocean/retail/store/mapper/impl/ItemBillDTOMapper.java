package com.mediaocean.retail.store.mapper.impl;

import com.mediaocean.retail.store.dto.Item;
import com.mediaocean.retail.store.dto.ItemBill;
import com.mediaocean.retail.store.mapper.Mapper;
import com.mediaocean.retail.store.model.Product;
import org.springframework.stereotype.Component;


@Component("ItemBillDTOMapper")
public class ItemBillDTOMapper implements Mapper<Item, ItemBill> {

    @Override
    public ItemBill map(Item item, Object... product) {
        if (item == null) {
            return null;
        }
        ItemBill itemBill = new ItemBill();
        itemBill.setProductID(item.getProductID());
        itemBill.setQuantity(item.getQuantity());
        if (product != null && product.length > 0) {
            Product dbProduct = (Product) product[0];
            itemBill.setRate(dbProduct.getRate());
            itemBill.setProductName(dbProduct.getName());
            itemBill.setSalesTaxPercentage(dbProduct.getCategory().getTaxPercentage());
        }

        return itemBill;
    }
}
