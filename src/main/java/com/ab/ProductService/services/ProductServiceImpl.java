package com.ab.ProductService.services;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Category;
import com.ab.ProductService.models.Product;
import com.ab.ProductService.repositories.CategoryRepository;
import com.ab.ProductService.repositories.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("Original")
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public  ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        throw new ProductNotFoundException("Product not found for id:"+id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProductById(Long id) {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public Product addProduct(Product product) {
//        Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
//        if(optionalCategory.isPresent()){
//            product.setCategory(optionalCategory.get());
//        }
//        else{
//            Category categoryResponse = categoryRepository.save(product.getCategory());
//            product.setCategory(categoryResponse);
//        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProductById(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product olderProduct = optionalProduct.get();
            Optional<Category> optionalCategory = categoryRepository.findByName(product.getCategory().getName());
            if(optionalCategory.isPresent()){
                product.setCategory(optionalCategory.get());
            }
            else{
                Category categoryResponse = categoryRepository.save(product.getCategory());
                product.setCategory(categoryResponse);
            }
            olderProduct.setCategory(product.getCategory());
            olderProduct.setTitle(product.getTitle());
            olderProduct.setPrice(product.getPrice());
            olderProduct.setDescription(product.getDescription());
            return productRepository.save(olderProduct);
        }
        else{
            throw new ProductNotFoundException("Prodcut not found for update of id:"+id);
        }


    }
}
