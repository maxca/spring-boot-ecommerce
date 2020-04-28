package com.ecommerce.repository.mapper;

import com.ecommerce.model.UserSession;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSessionMapper implements RowMapper<UserSession> {
    @Override
    public UserSession mapRow(ResultSet resultSet, int i) throws SQLException {
        UserSession session = new UserSession();
        session.setId(resultSet.getString("id"));
        session.setUserId(resultSet.getString("user_id"));
        session.setExpiredTime(resultSet.getString("expired_time"));
        return session;
    }
}
