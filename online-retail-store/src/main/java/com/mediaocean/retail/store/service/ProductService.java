package com.mediaocean.retail.store.service;

import com.mediaocean.retail.store.mapper.Mapper;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.model.ProductCategory;
import com.mediaocean.retail.store.repository.ProductCategoryRepository;
import com.mediaocean.retail.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductCategoryRepository categoryRepository;
    private final Mapper productDTOMapper;

    @Autowired
    public ProductService(ProductRepository repository,
                          ProductCategoryRepository categoryRepository,
                          @Qualifier("ProductDTOMapper") Mapper productDTOMapper ) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.productDTOMapper = productDTOMapper;
    }

    public List<com.mediaocean.retail.store.dto.Product> getAllProducts() {
        List<Product> dbProductList = (List<Product>) repository.findAll();
        return transformDBProductsToDTOProducts(dbProductList);
    }

    public com.mediaocean.retail.store.dto.Product getProductById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Input Product Id is null");
        }
        Product dbProduct = repository.findOne(id);
        return (com.mediaocean.retail.store.dto.Product) productDTOMapper.map(dbProduct, null);

    }

    public void deleteProductById(Long id) {
        if(id == null){
            throw new IllegalArgumentException("Input Product Id is null");
        }
        repository.delete(id);

    }

    public com.mediaocean.retail.store.dto.Product getProductByName(String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Input Product name is null or empty");
        }
        Product dbProduct = repository.findByName(name);
        if (dbProduct == null) {
            return null;
        }
        return (com.mediaocean.retail.store.dto.Product) productDTOMapper.map(dbProduct, null);

    }

    public List<com.mediaocean.retail.store.dto.Product> getProductsByCategory(String name) {

        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("Input Product category is null or empty");
        }
        ProductCategory category = categoryRepository.findByName(name);
        if (category == null){
            throw new IllegalArgumentException(String.format("Invalid Product category: %s",name));
        }
        List<Product> dbProductList = (List<Product>) repository.findByCategory(category);
        return transformDBProductsToDTOProducts(dbProductList);
    }

    public List<Product> getProductsByIds(List<Long> productIds){
        if(productIds == null || productIds.size() == 0){
            throw new IllegalArgumentException("Input Product category is null or empty");
        }
        return repository.findByIdIn(productIds);

    }

    private List<com.mediaocean.retail.store.dto.Product> transformDBProductsToDTOProducts ( List<Product> dbProductList){
        List<com.mediaocean.retail.store.dto.Product> dtoProductList = new ArrayList<>();
        if (dbProductList == null || dbProductList.size() == 0){
            return null;
        }
        dbProductList.forEach(dbProduct -> {
            dtoProductList.add((com.mediaocean.retail.store.dto.Product) productDTOMapper.map(dbProduct, null));
        });
        return dtoProductList;
    }
}
