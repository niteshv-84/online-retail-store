package com.mediaocean.retail.store.service;

import com.mediaocean.retail.store.dto.Bill;
import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.dto.Item;
import com.mediaocean.retail.store.dto.ItemBill;
import com.mediaocean.retail.store.mapper.impl.ItemBillDTOMapper;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.util.BillingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Slf4j
public class BillingService {

    private final ProductService productService;
    private final ItemBillDTOMapper itemBillDTOMapper;

    @Autowired
    public BillingService(ProductService productService,
                          ItemBillDTOMapper itemBillDTOMapper) {
        this.productService = productService;
        this.itemBillDTOMapper = itemBillDTOMapper;
    }

    public Bill generateBill(Cart cart) {
        validateCart(cart);
        Map<Long, Product> products = getProducts(cart);
        Bill bill = generateItemizedBill(cart.getItemList(), products);
        bill = generateTotalBill(bill);
        log.debug("Bill generated "+bill);
        return bill;
    }

    private Map<Long, Product> getProducts(Cart cart) {
        List<Long> productIds = cart.getItemList().stream().map(Item::getProductID).collect(Collectors.toList());
        List<Product> products = productService.getProductsByIds(productIds.stream().distinct().collect(Collectors.toList()));
        if (products == null) {
            throw new IllegalArgumentException("No Valid Products in Database for items in Cart");
        }
        log.debug("Products extracted");
        return products.stream().collect(Collectors.toMap(Product::getId,
                Function.identity()));
    }

    private Bill generateItemizedBill(List<Item> items, Map<Long, Product> products) {
        Bill bill = new Bill();
        items.forEach(item -> {
            Product dbProduct = products.get(item.getProductID());
            if (dbProduct == null) {
                throw new IllegalArgumentException("No Valid Products in Database for item");
            }
            ItemBill itemBill = itemBillDTOMapper.map(item, dbProduct);
            BigDecimal taxableAmount = BillingUtil.calculateAmount(itemBill.getRate(), itemBill.getQuantity());
            BigDecimal itemTax = BillingUtil.calculateTax(taxableAmount, itemBill.getSalesTaxPercentage());
            itemBill.setSalesTax(itemTax);
            itemBill.setTaxableAmount(taxableAmount);
            BigDecimal total = taxableAmount.add(itemTax);
            itemBill.setTotalAmount(total);
            bill.addItemBill(itemBill);
            log.debug("Itemized Bill generated"+itemBill);
        });
        return bill;
    }


    private Bill generateTotalBill(Bill bill) {
        BigDecimal totalTax = bill.getItemBillList().stream()
                .map(item -> item.getSalesTax())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        bill.setSalesTax(totalTax);
        BigDecimal totalTaxableAmount = bill.getItemBillList().stream()
                .map(item -> item.getTaxableAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        bill.setTaxableAmount(totalTaxableAmount);
        BigDecimal totalAmount = bill.getItemBillList().stream()
                .map(item -> item.getTotalAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        bill.setTotalAmount(totalAmount);
        return bill;
    }

    private void validateCart(Cart cart){
        if(cart == null || cart.getItemList() == null || cart.getItemList().size() == 0){
            throw new IllegalArgumentException("Invalid Cart Details: Null Cart or null/empty Item List");
        }
        cart.getItemList().forEach(item -> {
            if(item.getQuantity() == null || StringUtils.isEmpty(item.getProductID())){
                throw new IllegalArgumentException("Invalid Item details for Cart: Null/Emptry Quantity or Product ID");
            }
        });

    }
}
