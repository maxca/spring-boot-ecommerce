package com.ecommerce.controller;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.Product;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserSessionService;
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

    @Autowired
    private UserSessionService userSessionService;

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

    @PutMapping(path = "{productId}")
    public ResponseEntity<?> updatePersonById(
            @RequestHeader("sessionId") String sessionId,
            @PathVariable("productId") String productId,
            @Valid @NonNull @RequestBody Product productToUpdate) {
        UserSession session = userSessionService.validateSession(sessionId);
        if (null == session) {
            throw new BusinessException(404, "User Not found");
        }
        productToUpdate.setId(productId);
        productService.updateProduct(session.getUserId(), productToUpdate);
        Product product = productService.findProductById(productId);
        ResponseModel<Product> data = new ResponseModel<>(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productId") String productId) {
        Object product = productService.deleteProduct(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(product));
    }
}