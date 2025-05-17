package com.ab.ProductService.services;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product deleteProductById(Long id);
    Product addProduct(Product product);
    Product updateProductById(Long id, Product product) throws ProductNotFoundException ;
}
