package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<?> findProductById(@PathVariable("productId") String productId) {
        Product product = productService.findProductById(productId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(product));
    }
}