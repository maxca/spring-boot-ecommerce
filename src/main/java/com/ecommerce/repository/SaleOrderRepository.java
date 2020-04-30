package com.ecommerce.repository;

import com.ecommerce.model.SaleOrder;
import com.ecommerce.model.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.UUID;

import static com.ecommerce.helper.EncryptionHelper.md5;

@Repository
@Slf4j
public class SaleOrderRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Object createSaleOrder(ShoppingCart shoppingCart, SaleOrder saleOrder) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO sales_order(")
                .add("id, user_id, status, order_no, total_price, created_datetime)")
                .add("VALUES (:id, :userId, :status, :order_no, :total_price, now());");
        HashMap<String, Object> params = new HashMap<>();
        String id = UUID.randomUUID().toString();
        params.put("id", id);
        params.put("userId", shoppingCart.getUserId());
        params.put("status", saleOrder.getStatus());
        params.put("order_no", shoppingCart.getId());
        params.put("total_price", shoppingCart.getTotalPrice());
        try {
            namedParameterJdbcTemplate.update(sql.toString(), params);
            return params;
        } catch (QueryCreationException ex) {
            log.error("query error", ex.getMessage());
            return null;
        }
    }
}
