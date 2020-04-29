package com.ecommerce.repository.mapper;

import com.ecommerce.model.CartItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemMapper implements RowMapper<CartItem> {
    @Override
    public CartItem mapRow(ResultSet resultSet, int i) throws SQLException {
        CartItem item = new CartItem();
        item.setId(resultSet.getString("id"));
        item.setCartId(resultSet.getString("cart_id"));
        item.setProductId(resultSet.getString("product_id"));
        item.setQuantity(resultSet.getInt("quantity"));
        item.setPrice(resultSet.getFloat("price"));
        return item;
    }
}
