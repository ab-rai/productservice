package com.ab.ProductService.security.services;

import com.ab.ProductService.security.dtos.Token;
import com.ab.ProductService.security.dtos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public AuthenticationService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public boolean authenticate(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        Token tokenObj = Token.builder().value(token).build();
         ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("http://localhost:8081/users/validate", tokenObj, User.class);
        return userResponseEntity.hasBody();
    }

}
