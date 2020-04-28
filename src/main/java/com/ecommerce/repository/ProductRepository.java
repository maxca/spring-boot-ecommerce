package com.ecommerce.repository;

import com.ecommerce.model.Product;
import com.ecommerce.repository.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Product createProduct(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", product.getId());
        params.put("user_id", product.getUserId());
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("stock", product.getStock());
        StringJoiner insertSql = new StringJoiner(" ")
                .add("INSERT INTO products")
                .add("(")
                .add(getProductAllFields())
                .add(")")
                .add("VALUES")
                .add("(")
                .add(":id, :user_id, :name, :price, :stock")
                .add(");");

        try {
            int res = namedParameterJdbcTemplate.update(insertSql.toString(), params);
            return (res == 1) ? product : null;
        } catch (EmptyResultDataAccessException exception) {
            log.info("Cannot create product", insertSql.toString());
            return null;
        }
    }

    public Product findProductById(String productId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT " + getProductAllFields());
        sql.add("FROM products");
        sql.add("WHERE id = :productId");

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
        sql.add("SELECT " + getProductAllFields());
        sql.add("FROM products");

        try {
            return namedParameterJdbcTemplate.query(sql.toString(), new ProductMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("can't get products", sql.toString());
            return null;
        }
    }

    public int updateProduct(Product product) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE products SET")
                .add("name = :name")
                .add("price = :price")
                .add("stock = :stock");
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("stock", product.getStock());
        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }

    private String getProductAllFields() {
        return new StringJoiner(",")
                .add("id")
                .add("user_id")
                .add("name")
                .add("price")
                .add("stock")
                .toString();
    }
}
