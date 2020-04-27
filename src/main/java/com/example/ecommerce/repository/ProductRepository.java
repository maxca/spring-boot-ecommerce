package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product findProductById(String productId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select * from product where product_id = :productId;");

        HashMap<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new ProductMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("product not found", sql.toString());
            return null;
        }
    }

    public List<Product> getAllProduct() {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select * from product;");

        try {
            return namedParameterJdbcTemplate.query(sql.toString(), new ProductMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("can't get products", sql.toString());
            return null;
        }
    }

    public int updateProduct(Product product) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("update product set")
                .add("name = :name")
                .add("price = :price")
                .add("stock = :stock");
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("stock", product.getStock());
        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }
}
