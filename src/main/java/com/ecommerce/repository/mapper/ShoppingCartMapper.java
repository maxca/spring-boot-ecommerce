package com.ecommerce.repository.mapper;

import com.ecommerce.model.ShoppingCart;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingCartMapper implements RowMapper<ShoppingCart> {
    @Override
    public ShoppingCart mapRow(ResultSet resultSet, int i) throws SQLException {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(resultSet.getString("id"));
        cart.setUserId(resultSet.getString("user_id"));
        cart.setTotalPrice(resultSet.getFloat("total_price"));
        cart.setUpdatedDatetime(resultSet.getString("updated_datetime"));
        cart.setCreatedDatetime(resultSet.getString("created_datetime"));
        return cart;
    }

}
