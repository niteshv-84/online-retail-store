package com.mediaocean.retail.store.repository;


import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Nitesh on 9/5/2017.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);
    List<Product> findByCategory(ProductCategory category);
    List<Product> findByIdIn(List<Long> ids);
}
