package com.ecommerce.repository;

import com.ecommerce.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int createUser(User user) throws NoSuchAlgorithmException {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO users(")
                .add("id, email, password, name, phone, created_datetime, updated_datetime)")
                .add("VALUES (:id, :email, :password, :name, :phone, now(), now());");

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", UUID.randomUUID());
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        params.put("name", user.getName());
        params.put("phone", user.getPhone());
        return namedParameterJdbcTemplate.update(sql.toString(), params);
    }
}
