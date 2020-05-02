package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.RecordNotFoundException;
import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.User;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserSessionService userSessionService;

    @Autowired
    private UserService userService;

    private User mockUser() {
        return (new User())
                .setId(UUID.randomUUID().toString())
                .setName("KT")
                .setEmail("test@gmail.com");
    }

    private UserLoginRequest mocUserLoginRequest() {
        return (new UserLoginRequest())
                .setEmail("test@gmail.com")
                .setPassword("testPassword");
    }

    @Test
    public void createUserSuccess() {
        User mockUser = this.mockUser();
        doReturn(mockUser.getId())
                .when(userRepository)
                .createUser(any());
        doReturn(mockUser)
                .when(userRepository)
                .findUserById(anyString());
        User user = userService.createUser(mockUser);
        assertEquals(user, mockUser);
    }

    @Test
    public void createUserFailCanNotCreateUser() {
        User mockUser = this.mockUser();
        doThrow(new BusinessException(500, "can't create user"))
                .when(userRepository)
                .createUser(any());
        try {
            User user = userService.createUser(mockUser);
        } catch (BusinessException ex) {
            assertEquals("500", ex.getCode());
            assertEquals("can't create user", ex.getMessage());
        }
    }

    @Test
    public void createUserFailNotFoundUser() {
        User mockUser = this.mockUser();
        doReturn(mockUser.getId())
                .when(userRepository)
                .createUser(any());
        doThrow(new RecordNotFoundException(404, "not found user"))
                .when(userRepository)
                .findUserById(anyString());
        try {
            User user = userService.createUser(mockUser);
        } catch (RecordNotFoundException ex) {
            assertEquals("404", ex.getCode());
            assertEquals("not found user", ex.getMessage());
        }
    }

    @Test
    public void userLoginSuccess() {
        UserLoginRequest request = this.mocUserLoginRequest();
        UserSession session = new UserSession();
        User mockUser = this.mockUser();
        doReturn(mockUser)
                .when(userRepository)
                .login(any());
        doReturn(mockUser)
                .when(userRepository)
                .updateLastLogin(anyString());
        doReturn(session)
                .when(userSessionService)
                .createUserSession(anyString());

        UserSession userSession = userService.login(request);
        assertEquals(session, userSession);
    }

    @Test
    public void userLoginUnauthorized() {
        UserLoginRequest request = this.mocUserLoginRequest();
        doThrow(new UnauthorizedException(401, "Unauthorized service"))
                .when(userRepository)
                .login(any());
        try {
            UserSession userSession = userService.login(request);
        } catch (UnauthorizedException ex) {
            assertEquals("401", ex.getCode());
            assertEquals("Unauthorized service", ex.getMessage());
        }
    }

    @Test
    public void userLoginUpdateLastLoginFail() {
        UserLoginRequest request = this.mocUserLoginRequest();
        UserSession session = new UserSession();
        User mockUser = this.mockUser();
        doReturn(mockUser)
                .when(userRepository)
                .login(any());
        doThrow(new BusinessException(500, "Update Last login fail"))
                .when(userRepository)
                .updateLastLogin(anyString());
        try {
            UserSession userSession = userService.login(request);
        } catch (BusinessException ex) {
            assertEquals("500", ex.getCode());
            assertEquals("Update Last login fail", ex.getMessage());
        }
    }
}