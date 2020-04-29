package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CartItem {
    private String id;
    private String cartId;
    @NotNull
    private String productId;
    private Float price;
    private Integer quantity;
}
