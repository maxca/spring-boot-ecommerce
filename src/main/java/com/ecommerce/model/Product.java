package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
    private String id;
    private String userId;
    private String name;
    private Float price;
    private Integer stock;
}
