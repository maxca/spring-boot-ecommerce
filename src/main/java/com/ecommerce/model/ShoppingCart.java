package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ShoppingCart {
    private String id;
    private String userId;
    private Float totalPrice;
    private String updatedDatetime;
    private List<CartItem> items;
}
