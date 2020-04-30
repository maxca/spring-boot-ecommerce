package com.ecommerce.controller;

import com.ecommerce.model.SaleOrder;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/v1/sale-order")
@Slf4j
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    @PostMapping
    public ResponseEntity<?> checkout(@RequestBody @Valid SaleOrder saleOrder){
        Object saleOrder1 = saleOrderService.checkout(saleOrder);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(saleOrder1));
    }
}
