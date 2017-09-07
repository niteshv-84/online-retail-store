package com.mediaocean.retail.store.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class Bill {

    private List<ItemBill> itemBillList = new ArrayList<>();
    private BigDecimal taxableAmount;
    private BigDecimal salesTax;
    private BigDecimal totalAmount;

    public void addItemBill(ItemBill itemBill){
        itemBillList.add(itemBill);
    }
}
