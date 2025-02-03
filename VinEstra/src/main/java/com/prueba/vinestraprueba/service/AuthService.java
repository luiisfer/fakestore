package com.prueba.vinestraprueba.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String url = "https://fakestoreapi.com/auth/login";

    public String login(String username, String password) {
        System.out.println("login url " + url);
        String requestBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                String.class
        );

        return response.getBody();
    }

    public boolean isValidToken(String token) {
        if (token == null || token.isEmpty()) {

            return false;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String url = "https://fakestoreapi.com/"; // Suponiendo que este es un endpoint de validación
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class);


            return response.getStatusCode() == HttpStatus.OK;

        } catch (Exception e) {
            return false;
        }

    }
}
