package com.mediaocean.retail.store.service;

import com.mediaocean.retail.store.mapper.Mapper;
import com.mediaocean.retail.store.mapper.impl.ProductDTOMapper;
import com.mediaocean.retail.store.model.Product;
import com.mediaocean.retail.store.model.ProductCategory;
import com.mediaocean.retail.store.repository.ProductCategoryRepository;
import com.mediaocean.retail.store.repository.ProductRepository;
import com.mediaocean.retail.store.testutil.TestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository repository;
    private ProductCategoryRepository categoryRepository;
    private Mapper productDTOMapper;
    private ProductService service;
    private Product product;
    private com.mediaocean.retail.store.dto.Product dtoProduct;

    @Before
    public void setUp() {

        repository = mock(ProductRepository.class);
        categoryRepository = mock(ProductCategoryRepository.class);
        productDTOMapper = mock(ProductDTOMapper.class);
        service = new ProductService(repository, categoryRepository, productDTOMapper);
        product = TestDataBuilder.getProduct();
        dtoProduct = TestDataBuilder.getDtoProduct();
    }

    @Test
    public void getProductByIdWhenValidInput() {
        Long id = 1L;

        when(repository.findOne(id)).thenReturn(product);
        when(productDTOMapper.map(product, null)).thenReturn(dtoProduct);
        com.mediaocean.retail.store.dto.Product resultProduct = service.getProductById(id);
        assertEquals(dtoProduct, resultProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductByIdWhenNullInput() {
        service.getProductById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductByNameWhenNullInput() {
        service.getProductByName(null);
    }

    @Test
    public void getProductByNameWhenValidInput() {
        String name = "Oil";
        when(repository.findByName(name)).thenReturn(product);
        when(productDTOMapper.map(product, null)).thenReturn(dtoProduct);
        com.mediaocean.retail.store.dto.Product resultProduct = service.getProductByName(name);
        assertEquals(dtoProduct, resultProduct);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductsByCategoryWhenInputNull() {
        service.getProductsByCategory(null);
    }

    @Test
    public void getProductByCategoryWhenValidInput() {
        String categoryName = "A";
        List<Product> products = new ArrayList<>();
        products.add(product);
        ProductCategory category = TestDataBuilder.getCategoryA();
        when(categoryRepository.findByName(categoryName)).thenReturn(category);
        when(repository.findByCategory(category)).thenReturn(products);
        when(productDTOMapper.map(product, null)).thenReturn(dtoProduct);
        List<com.mediaocean.retail.store.dto.Product> resultProductList = service.getProductsByCategory(categoryName);
        assertNotNull(resultProductList);
        assertEquals(1, resultProductList.size());
        assertEquals(dtoProduct, resultProductList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductByCategoryWhenCategoryNotPresent() {
        String categoryName = "A";
        List<Product> products = new ArrayList<>();
        products.add(product);
        ProductCategory category = TestDataBuilder.getCategoryA();
        when(categoryRepository.findByName(categoryName)).thenReturn(null);
        when(repository.findByCategory(category)).thenReturn(products);
        when(productDTOMapper.map(product, null)).thenReturn(dtoProduct);
        service.getProductsByCategory(categoryName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductsByIdsWhenInputNull() {
        service.getProductsByIds(null);
    }

    @Test
    public void getProductByIdsWhenValidInput() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        List<Product> products = new ArrayList<>();
        products.add(product);
        ProductCategory category = TestDataBuilder.getCategoryA();
        when(repository.findByIdIn(ids)).thenReturn(products);
        List<Product> resultProductList = service.getProductsByIds(ids);
        assertNotNull(resultProductList);
        assertEquals(1, resultProductList.size());
        assertEquals(product, resultProductList.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getProductsByIdsWhenInputEmpty() {
        service.getProductsByIds(new ArrayList<Long>());
    }

    @Test
    public void getAllProductsWhenValidDataPresent() {

        List<Product> products = new ArrayList<>();
        products.add(product);
        when(repository.findAll()).thenReturn(products);
        when(productDTOMapper.map(product, null)).thenReturn(dtoProduct);
        List<com.mediaocean.retail.store.dto.Product> resultProductList = service.getAllProducts();
        assertNotNull(resultProductList);
        assertEquals(1, resultProductList.size());
        assertEquals(dtoProduct, resultProductList.get(0));
    }
}
