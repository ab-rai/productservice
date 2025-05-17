package com.ab.ProductService.thirdpartyclients;

import com.ab.ProductService.dtos.FakeStoreProductDto;
import com.ab.ProductService.exceptions.ProductNotFoundException;
import com.ab.ProductService.models.Category;
import com.ab.ProductService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String FAKE_URL;
    private String FAKE_URL_ID;

    @Autowired
    public  FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                            @Value("${fakestore.api.url}") String FAKE_URL,
                            @Value("${fakestore.api.url.id}") String FAKE_URL_ID){
        this.restTemplateBuilder = restTemplateBuilder;
        this.FAKE_URL = FAKE_URL;
        this.FAKE_URL_ID = FAKE_URL_ID;

    }
    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(FAKE_URL_ID, FakeStoreProductDto.class,id);
        if(responseEntity.getBody() == null){
            throw new ProductNotFoundException("Product not found for id:"+id);
        }
        return responseEntity.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity(FAKE_URL+"/", FakeStoreProductDto[].class);
        assert responseEntity.getBody() != null;
        return List.of(responseEntity.getBody());
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.delete(FAKE_URL_ID,id);
//        RestTemplate's delete returns void so to get the object we have to write code like
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(FAKE_URL_ID, HttpMethod.DELETE,requestCallback, responseExtractor,id);
        assert responseEntity != null;
        return responseEntity.getBody();

    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.postForEntity(FAKE_URL,
                fakeStoreProductDto, FakeStoreProductDto.class);
        return responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, FakeStoreProductDto fakeStoreProductDto)
            throws ProductNotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity;
        try {
            restTemplate.put(FAKE_URL_ID, fakeStoreProductDto, id);
            responseEntity = restTemplate.execute(FAKE_URL_ID, HttpMethod.PUT,requestCallback, responseExtractor,id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new ProductNotFoundException("Product not found for id:"+id);
        }
        assert responseEntity != null;
        return responseEntity.getBody();

    }

}
