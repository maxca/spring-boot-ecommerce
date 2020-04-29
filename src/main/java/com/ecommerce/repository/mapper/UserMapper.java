package com.ecommerce.repository.mapper;

import com.ecommerce.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPhone(resultSet.getString("phone"));
        user.setName(resultSet.getString("name"));
        user.setCreatedDatetime(resultSet.getString("created_datetime"));
        user.setUpdatedDatetime(resultSet.getString("updated_datetime"));
        return user;
    }
}
