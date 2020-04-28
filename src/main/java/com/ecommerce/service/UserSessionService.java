package com.ecommerce.service;

import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.UserSession;
import com.ecommerce.repository.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSessionService {

    @Autowired
    private UserSessionRepository userSessionRepository;

    public UserSession validateSession(String sessionId) {
        UserSession session = userSessionRepository.findUserSessionBySessionId(sessionId);
        if (null == session) {
            throw new UnauthorizedException(404, "Unauthorized service");
        }
        return session;
    }

    public UserSession createUserSession(String userId) {
        String sessionId = userSessionRepository.createUserSession(userId);
        if (null == sessionId) {
            throw new UnauthorizedException(404, "Unauthorized can't create session");
        }
        return this.validateSession(sessionId);
    }
}
