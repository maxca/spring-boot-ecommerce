package com.ecommerce.repository;

import com.ecommerce.model.Product;
import com.ecommerce.model.SaleOrder;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.repository.mapper.ProductMapper;
import com.ecommerce.repository.mapper.SaleOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import static com.ecommerce.helper.EncryptionHelper.md5;

@Repository
@Slf4j
public class SaleOrderRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Object createSaleOrder(ShoppingCart shoppingCart) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO sales_order(")
                .add("id, user_id, status, order_no, total_price, created_datetime)")
                .add("VALUES (:id, :userId, :status, :orderNo, :totalPrice, now());");
        HashMap<String, Object> params = new HashMap<>();
        String id = UUID.randomUUID().toString();
        params.put("id", id);
        params.put("userId", shoppingCart.getUserId());
        params.put("status", "waiting");
        params.put("orderNo", shoppingCart.getId());
        params.put("totalPrice", shoppingCart.getTotalPrice());
        try {
            namedParameterJdbcTemplate.update(sql.toString(), params);
            return params;
        } catch (QueryCreationException ex) {
            log.error("query error", ex.getMessage());
            return null;
        }
    }

    private String getSaleOrderAllFields() {
        return new StringJoiner(",")
                .add("id")
                .add("status")
                .add("total_price")
                .add("user_id")
                .add("order_no")
                .add("created_datetime")
                .toString();
    }

    public List<SaleOrder> getAllSaleOrder() {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT " + getSaleOrderAllFields());
        sql.add("FROM sales_order");
        try {
            return namedParameterJdbcTemplate.query(sql.toString(), new SaleOrderMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("can't get products", sql.toString());
            return null;
        }
    }
}
