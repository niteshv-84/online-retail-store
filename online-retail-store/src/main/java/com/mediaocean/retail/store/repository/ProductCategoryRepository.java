package com.mediaocean.retail.store.repository;

import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Nitesh on 9/6/2017.
 */
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    ProductCategory findByName(String name);

}
