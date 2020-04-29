package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/shopping_cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public ResponseEntity<?> addToCart(
            @RequestHeader("sessionId") String sessionId,
            @Valid @NonNull @RequestBody CartItem item) {
        ShoppingCart cart = shoppingCartService.addToCart(sessionId, item);
        ResponseModel<ShoppingCart> data = new ResponseModel<>(cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @GetMapping
    public ResponseEntity<?> getShoppingCart(@RequestHeader("sessionId") String sessionId) {
        ShoppingCart cart = shoppingCartService.getShoppingCart(sessionId);
        ResponseModel<ShoppingCart> data = new ResponseModel<>(cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
}