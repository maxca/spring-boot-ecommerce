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
    private int totalQty;
    private String updatedDatetime;
    private String createdDatetime;
    private List<CartItem> items;
}
