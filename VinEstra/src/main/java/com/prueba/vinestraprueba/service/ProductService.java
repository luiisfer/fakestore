package com.prueba.vinestraprueba.service;

import com.prueba.vinestraprueba.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductEntity> getProductsFromExternalApi() {
        String url = "https://fakestoreapi.com/products";
        ProductEntity[] productosArray = restTemplate.getForObject(url, ProductEntity[].class);
        return Arrays.asList(productosArray);
    }

    public ProductEntity getProductById(Long id) {
        String url = "https://fakestoreapi.com/products/" + id;
        return restTemplate.getForObject(url, ProductEntity.class);
    }

    public ProductEntity addProduct(ProductEntity newProduct) {
        String url = "https://fakestoreapi.com/products";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ProductEntity> request = new HttpEntity<>(newProduct, headers);

        return restTemplate.postForObject(url, request, ProductEntity.class);
    }

    public void deleteProduct(Long id) {
        String url = "https://fakestoreapi.com/products/" + id;
        try {
            restTemplate.delete(url);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("Product not not found with ID: " + id);
        }
    }
}
