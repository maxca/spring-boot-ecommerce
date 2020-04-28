package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<?> findProductById(
            @PathVariable("productId") String productId,
            @RequestHeader("session_id") String sessionId
    ) {
        Product product = productService.findProductById(sessionId, productId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(product));
    }
}