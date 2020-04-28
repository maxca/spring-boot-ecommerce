package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestHeader("sessionId") String sessionId,
            @Valid @NonNull @RequestBody Product newProduct) {
        Product product = productService.createProduct(sessionId, newProduct);
        ResponseModel<Product> data = new ResponseModel<>(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<?> findProductById(
            @PathVariable("productId") String productId
    ) {
        Product product = productService.findProductById(productId);
        ResponseModel<Product> data = new ResponseModel<>(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        ResponseModel<List<Product>> data = new ResponseModel<>(products);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
}