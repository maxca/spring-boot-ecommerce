package com.ecommerce.repository;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.ShoppingCart;
import com.ecommerce.repository.mapper.CartItemMapper;
import com.ecommerce.repository.mapper.ShoppingCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.QueryCreationException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class ShoppingCartRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ShoppingCart addToCart(String userId, CartItem item) {
        ShoppingCart cart = getShoppingCart(userId);
        CartItem cartItem = getCartItem(cart.getId(), item.getProductId());
        Map<String, Object> params = new HashMap<>();
        params.put("cart_id", cart.getId());
        params.put("product_id", item.getProductId());
        params.put("price", item.getPrice());

        StringJoiner sql = new StringJoiner(" ");
        if (cartItem == null) {
            params.put("id", UUID.randomUUID().toString());
            params.put("quantity", item.getQuantity());
            sql.add("INSERT INTO cart_item(")
                    .add("id, cart_id, product_id, quantity, price)")
                    .add("VALUES (:id, :cart_id, :product_id, :quantity, :price);");
        } else {
            params.put("id", cartItem.getId());
            params.put("quantity", cartItem.getQuantity() + item.getQuantity());
            sql.add("UPDATE cart_item")
                    .add("SET quantity = :quantity, price = :price")
                    .add("WHERE id = :id");
        }
        try {
            namedParameterJdbcTemplate.update(sql.toString(), params);
            return getShoppingCart(userId);
        } catch (QueryCreationException ex) {
            log.error("query error", ex.getMessage());
            return null;
        }
    }

    public ShoppingCart getShoppingCart(String userId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT id, user_id, total_price, total_qty, created_datetime, updated_datetime");
        sql.add("FROM shopping_cart");
        sql.add("WHERE user_id = :user_id");
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);

        log.info("userId", userId);
        try {
            ShoppingCart cart = namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new ShoppingCartMapper());
            List<CartItem> cartItems = getCartItems(cart.getId());
            cart.setItems(cartItems);
            return cart;
        } catch (EmptyResultDataAccessException exception) {
            log.info("query not found", sql.toString());
            return createShippingCart(userId);
        }
    }

    private ShoppingCart createShippingCart(String userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(UUID.randomUUID().toString());
        cart.setUserId(userId);

        Map<String, Object> params = new HashMap<>();
        params.put("id", cart.getId());
        params.put("user_id", cart.getUserId());

        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO shopping_cart(")
            .add("id, user_id, created_datetime, updated_datetime)")
            .add("VALUES (:id, :user_id, now(), now());");

        try {
            int res = namedParameterJdbcTemplate.update(sql.toString(), params);
            return (res == 1) ? getShoppingCart(userId) : null;
        } catch (QueryCreationException ex) {
            log.error("insert error", ex.getMessage());
            return null;
        }
    }

    private CartItem getCartItem(String cartId, String productId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT id, cart_id, product_id, quantity, price");
        sql.add("FROM cart_item");
        sql.add("WHERE cart_id = :cart_id AND product_id = :product_id");
        Map<String, Object> params = new HashMap<>();
        params.put("cart_id", cartId);
        params.put("product_id", productId);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new CartItemMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("query not found", sql.toString());
            return null;
        }
    }

    private List<CartItem> getCartItems(String cartId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT id, cart_id, product_id, quantity, price");
        sql.add("FROM cart_item");
        sql.add("WHERE cart_id = :cart_id");
        Map<String, Object> params = new HashMap<>();
        params.put("cart_id", cartId);

        try {
            return namedParameterJdbcTemplate.query(sql.toString(), params, new CartItemMapper());
        } catch (EmptyResultDataAccessException exception) {
            log.info("query not found", sql.toString());
            return null;
        }
    }
}
