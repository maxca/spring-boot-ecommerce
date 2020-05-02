package com.ecommerce.repository;

import com.ecommerce.model.User;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.UUID;

import static com.ecommerce.helper.EncryptionHelper.md5;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String createUser(User user) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO users(")
                .add("id, email, password, name, phone, created_datetime, updated_datetime)")
                .add("VALUES (:id, :email, :password, :name, :phone, now(), now());");

        HashMap<String, Object> params = new HashMap<>();
        String userId = UUID.randomUUID().toString();
        params.put("id", userId);
        params.put("email", user.getEmail());
        params.put("password", md5(user.getPassword()));
        params.put("name", user.getName());
        params.put("phone", user.getPhone());
        try {
            namedParameterJdbcTemplate.update(sql.toString(), params);
            return userId;
        } catch (DataIntegrityViolationException ex) {
            return null;
        }

    }

    public Object editProfile(User user) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE users ")
                .add("SET name = :name, phone = :phone, updated_datetime = now() ")
                .add("WHERE id = :id;");
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("name", user.getName());
        params.put("phone", user.getPhone());
        try {
            return namedParameterJdbcTemplate.update(sql.toString(), params);
        } catch (Exception ex) {
            return null;
        }
    }

    public User login(UserLoginRequest request) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select id ,email, phone ,name ,created_datetime, updated_datetime from users")
                .add("where users.email =:email")
                .add("and users.password =:password;");
        HashMap<String, Object> params = new HashMap<>();

        params.put("email", request.getEmail());
        params.put("password", md5(request.getPassword()));
        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new UserMapper());
        } catch (EmptyResultDataAccessException ex) {
            log.error("not found user");
            return null;
        }
    }

    public Object updateLastLogin(String userId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("update users set")
                .add("last_login = now()")
                .add("where id =:userId");
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        try {
            return namedParameterJdbcTemplate.update(sql.toString(), params) == 1 ? 1 : null;
        } catch (Exception ex) {
            return null;
        }
    }

    public User findUserById(String userId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select id ,email, phone ,name, last_login, created_datetime, updated_datetime from users")
                .add("where id =:userId");

        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new UserMapper());
        } catch (EmptyResultDataAccessException ex) {
            log.error("not found user");
            return null;
        }
    }
}
