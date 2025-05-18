package com.ab.ProductService.controllers;

import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Product;
import com.ab.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    @Qualifier("Original")
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product  getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product  deleteProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@RequestBody Product product, @PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.updateProductById(id, product);
    }

//    @GetMapping("/category/{id}")
//    public List<String> getProductsByCategory(Long id){
//        return productService.getProductsByCategory(id);
//    }

//    @GetMapping("/")
//    public List<Product> getAllProducts(){
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/{id}")
//    public Product getProductById(@PathVariable("id") Long id){
//        return productService.getProductById(id);
//    }
//
//    @GetMapping("/category/{id}")
//    public List<Product> getProductsByCategory(Long id){
//        return productService.getProductsByCategory(id);
//    }





//
//    @PutMapping("/{id}/{product}")
//    public Boolean updateProduct(Long id, Product product){
//        return productService.updateProduct(id, product);
//    }
//
//    @DeleteMapping("/{id}")
//    public Boolean deleteProductById(Long id){
//        return productService.deleteProductById(id);
//    }
//


}
