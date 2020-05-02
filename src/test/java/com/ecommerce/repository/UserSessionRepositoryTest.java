package com.ecommerce.repository;

import com.ecommerce.model.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.StringJoiner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Slf4j
public class UserSessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Before
    public void setUp() throws Exception {
        StringJoiner sqlInsertUser = new StringJoiner(" ");
        sqlInsertUser.add("INSERT INTO users(")
                .add("id, email, password, name, phone, created_datetime, updated_datetime)")
                .add("VALUES (1, 'test@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 'KT', '0996217524', now(), now());");
        jdbcTemplate.execute(sqlInsertUser.toString());

        StringJoiner sqlInsertSession = new StringJoiner(" ");
        sqlInsertSession.add("INSERT INTO users_session(")
                .add("id, user_id, expired_datetime, created_datetime, updated_datetime)")
                .add("VALUES (2, 1, now() +'1 hours'::interval, now(), now());");
        jdbcTemplate.execute(sqlInsertSession.toString());

    }

    @Test
    public void findUserSessionBySessionIdSuccess() {
        UserSession session = userSessionRepository.findUserSessionBySessionId("2");
        assertEquals("2", session.getId());
        assertEquals("1", session.getUserId());
    }

    @Test
    public void findUserSessionBySessionIdFail() {
        UserSession session = userSessionRepository.findUserSessionBySessionId("textIDX");
        assertNull(session);
    }

    @Test
    public void createUserSessionSuccess() {
        String sessionId = userSessionRepository.createUserSession("1");
        assertNotNull(sessionId);
    }

    @Test
    public void createUserSessionFail() {
        String sessionId = userSessionRepository.createUserSession("testIDX");
        assertNull(sessionId);
    }
}