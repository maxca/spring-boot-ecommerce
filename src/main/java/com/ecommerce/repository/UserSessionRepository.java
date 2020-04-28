package com.ecommerce.repository;

import com.ecommerce.model.UserSession;
import com.ecommerce.repository.mapper.UserSessionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.StringJoiner;

@Repository
@Slf4j
public class UserSessionRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserSession findUserSessionBySessionId(String sessionId) {
        StringJoiner sql = new StringJoiner(" ");
        sql.add("select id, user_id, expired_datetime")
                .add("from users_session")
                .add("where id = :sessionId and")
                .add("expired_datetime > now()")
                .add("limit 1;");

        HashMap<String, Object> params = new HashMap<>();
        params.put("sessionId", sessionId);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql.toString(), params, new UserSessionMapper());
        } catch (EmptyResultDataAccessException ex) {
            log.error("select user session query :", sql.toString());
            return null;
        }

    }
}
