package com.ecommerce.service;

import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.UserSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserSessionServiceTest {

    @MockBean
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserSessionService userSessionService;

    private UserSession mockUserSession() {
        return (new UserSession())
                .setId(UUID.randomUUID().toString())
                .setUserId(UUID.randomUUID().toString());

    }

    @Test
    void createUserSessionSuccess() {
        UserSession mockSession = this.mockUserSession();
        doReturn(mockSession.getId())
                .when(userSessionRepository)
                .createUserSession(any());

        doReturn(mockSession)
                .when(userSessionRepository)
                .findUserSessionBySessionId(any());

        UserSession sessionId = userSessionService.createUserSession("1");
        assertEquals(sessionId, mockSession);
    }

    @Test
    void createUserSessionUnauthorized() {
        doReturn(null)
                .when(userSessionRepository)
                .createUserSession(any());
        try {
            UserSession sessionId = userSessionService.createUserSession("1");
        } catch (UnauthorizedException ex) {
            assertEquals("401", ex.getCode());
            assertEquals("Unauthorized can't create session", ex.getMessage());
        }
    }

    @Test
    void validateSessionSuccess() {
        UserSession mockSession = this.mockUserSession();
        doReturn(mockSession)
                .when(userSessionRepository)
                .findUserSessionBySessionId(any());
        UserSession session = userSessionService.validateSession("1");
        assertEquals(mockSession, session);
    }

    @Test
    void validateSessionUnauthorized() {
        doReturn(null)
                .when(userSessionRepository)
                .findUserSessionBySessionId(any());
        try {
            UserSession session = userSessionService.validateSession("1");
        } catch (UnauthorizedException ex) {
            assertEquals("401", ex.getCode());
            assertEquals("Unauthorized service", ex.getMessage());
        }
    }

}