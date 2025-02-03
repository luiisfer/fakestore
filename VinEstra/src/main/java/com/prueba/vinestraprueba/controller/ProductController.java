package com.prueba.vinestraprueba.controller;

import com.prueba.vinestraprueba.entity.ProductEntity;
import com.prueba.vinestraprueba.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.vinestraprueba.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getProductos() {

        return productService.getProductsFromExternalApi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductoById(@PathVariable Long id) {
        ProductEntity producto = productService.getProductById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity newProduct) {
        ProductEntity productAdded = productService.addProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(productAdded);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
