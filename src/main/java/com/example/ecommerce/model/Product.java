package com.example.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {

    private String name;
    private String price;
    private String stock;
}
