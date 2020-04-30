package com.ecommerce.controller;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.ShoppingCartService;
import com.ecommerce.service.UserSessionService;
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

    @Autowired
    private UserSessionService userSessionService;

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
        UserSession session = userSessionService.validateSession(sessionId);
        ShoppingCart cart = shoppingCartService.getShoppingCart(session.getUserId());
        ResponseModel<ShoppingCart> data = new ResponseModel<>(cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }

    @DeleteMapping(path = "item/{id}")
    public ResponseEntity<?> deleteProductInCart(
            @PathVariable("id") String id,
            @RequestHeader("sessionId") String sessionId) {
        UserSession session = userSessionService.validateSession(sessionId);
        shoppingCartService.deleteProductInCart(id);
        ShoppingCart cart = shoppingCartService.getShoppingCart(session.getUserId());
        ResponseModel<ShoppingCart> data = new ResponseModel<>(cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(data);
    }
}
