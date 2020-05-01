package com.ecommerce.repository.mapper;

import com.ecommerce.model.SaleOrder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleOrderMapper implements RowMapper<SaleOrder> {
    @Override
    public SaleOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setId(resultSet.getString("id"));
        saleOrder.setStatus(resultSet.getString("status"));
        saleOrder.setTotalPrice(resultSet.getFloat("total_price"));
        saleOrder.setUserId(resultSet.getString("user_id"));
        saleOrder.setOrderNo(resultSet.getString("order_no"));
        saleOrder.setCreatedDatetime(resultSet.getString("created_datetime"));
        return saleOrder;
    }
}
