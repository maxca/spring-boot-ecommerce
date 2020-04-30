package com.ecommerce.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SaleOrder {
    private String id;
    private String status;
    private Float totalPrice;
    private String userId;
    private String orderNo;
    private String createdDatetime;
}
