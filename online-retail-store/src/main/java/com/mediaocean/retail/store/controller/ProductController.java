package com.mediaocean.retail.store.controller;


import com.mediaocean.retail.store.dto.Bill;
import com.mediaocean.retail.store.dto.Cart;
import com.mediaocean.retail.store.dto.Product;
import com.mediaocean.retail.store.service.BillingService;
import com.mediaocean.retail.store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller/REST endpoint to for product related operations
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        log.debug("Entering get all product method");
        return service.getAllProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable("id") Long id) {
        log.debug("Entering get product by id method for id: "+id);
        return service.getProductById(id);
    }

    @RequestMapping(params = {"name"}, method = RequestMethod.GET)
    public Product getProductByName(@RequestParam("name") String name) {
        log.debug("Entering get product by name method for name: "+name);
        return service.getProductByName(name);
    }

    @RequestMapping(params = {"category"}, method = RequestMethod.GET)
    public List<Product> getProductByCategory(@RequestParam("category") String category) {
        log.debug("Entering get product by category method for category: "+category);
        return service.getProductsByCategory(category);
    }

    @RequestMapping(method= RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        log.debug("Entering create product method: "+product);
        //TODO :: Add actual implementation. The No-OP implementation here is only to demo the security feature.
        return  product;
    }
}
