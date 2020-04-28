package com.ecommerce.repository.mapper;

import com.ecommerce.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper  implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getString("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getFloat("price"));
        product.setStock(resultSet.getInt("stock"));
        return product;
    }
}
