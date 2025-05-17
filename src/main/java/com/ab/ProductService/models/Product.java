package com.ab.ProductService.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Product extends BaseModel {

//    private Long id;
    private String title;
    private String description;
    private Long price;
    private Category  category;
    private List<String> allowedUsers;
}
