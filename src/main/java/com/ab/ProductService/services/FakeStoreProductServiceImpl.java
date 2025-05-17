package com.ab.ProductService.services;

import com.ab.ProductService.dtos.FakeStoreProductDto;
import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Category;
import com.ab.ProductService.models.Product;
import com.ab.ProductService.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("Fake")
public class FakeStoreProductServiceImpl implements ProductService {
    FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreProductDto(fakeStoreClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto dto : fakeStoreClient.getAllProducts()) {
            products.add(getProductFromFakeStoreProductDto(dto));
        }
        return products;
    }

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeStoreProductDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreProductDto(
                fakeStoreClient.addProduct(getFakeStoreProductDtoFromProduct(product)));
    }

    @Override
    public Product updateProductById(Long id, Product product) throws ProductNotFoundException {
         return getProductFromFakeStoreProductDto(
                 fakeStoreClient.updateProductById(id, getFakeStoreProductDtoFromProduct(product)));
    }

    private Product getProductFromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Category category = Category.builder()
                .name(fakeStoreProductDto.getCategory())
                .build();

        return Product.builder()
                .id(fakeStoreProductDto.getId())
                .title(fakeStoreProductDto.getTitle())
                .description(fakeStoreProductDto.getDescription())
                .price(fakeStoreProductDto.getPrice())
                .category(category)
                .build();
    }

    private FakeStoreProductDto getFakeStoreProductDtoFromProduct(Product product) {
        return FakeStoreProductDto.builder()
                .title(product.getTitle())
                .description(product.getDescription())
                .category(product.getCategory().getName())
                .price(product.getPrice())
                .build();
    }

}
