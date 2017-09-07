package com.mediaocean.retail.store.controller;

import com.mediaocean.retail.store.dto.Bill;
import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.service.BillingService;
import com.mediaocean.retail.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller/REST endpoint to generate the bill based on the CART items
 */
@RestController
@RequestMapping("/bills")
@Slf4j
public class BillingController {

    private final BillingService service;

    @Autowired
    public BillingController(BillingService service)  {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Bill generateBill(@RequestBody Cart cart)  {
        log.debug("Entering generateBill method with input"+cart);
        return service.generateBill(cart);
    }

}
