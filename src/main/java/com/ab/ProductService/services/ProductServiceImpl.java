package com.ab.ProductService.services;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("original")
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProductById(Long id, Product product) throws ProductNotFoundException {
        return null;
    }
}
