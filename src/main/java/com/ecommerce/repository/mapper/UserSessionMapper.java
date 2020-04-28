package com.ecommerce.repository.mapper;

import com.ecommerce.model.UserSession;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSessionMapper implements RowMapper<UserSession> {
    @Override
    public UserSession mapRow(ResultSet resultSet, int i) throws SQLException {
        UserSession userSession = new UserSession();
        userSession.setId(resultSet.getString("id"));
        userSession.setUserId(resultSet.getString("user_id"));
        userSession.setExpiredTime(resultSet.getString("user_id"));
        return userSession;
    }
}
