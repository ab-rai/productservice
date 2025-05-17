package com.ab.ProductService.dtos;

import com.ab.ProductService.models.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String category;
}
