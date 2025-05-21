package com.ab.ProductService.security.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token{
    private String value;
    private Date expiryAt;
    private Boolean deleted;
}
