package com.ab.ProductService.controllers;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Product;
import com.ab.ProductService.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockitoBean
    @Qualifier("Original")
    private ProductService productService;

//    @Test
//    void getProductById() throws ProductNotFoundException {
//        // 1. Arrange
//        Product dummyProduct = new Product();
//        dummyProduct.setId(1L);
//        dummyProduct.setTitle("dummy");
//        when(productService.getProductById(1L)).thenReturn(dummyProduct);
//
//        // 2. Act
//        Product product = productController.getProductById(1L);
//
//        // 3. Assert
//        assertEquals(1L, product.getId());
//    }
//
//    @Test
//    void getProductByIdThrowsException() throws ProductNotFoundException {
//        when(productService.getProductById(1L)).thenThrow( new ProductNotFoundException("Not found "));
//        assertThrows(ProductNotFoundException.class, ()->productController.getProductById(1L));
//    }
}