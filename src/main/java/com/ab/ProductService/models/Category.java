package com.ab.ProductService.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Category extends BaseModel {

    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @JsonManagedReference
    private List<Product> products;
    public Category(String name){
        this.name = name;
    }
}
