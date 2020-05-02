package com.ecommerce.controller;

import com.ecommerce.BaseTest;
import com.ecommerce.model.User;
import com.ecommerce.model.UserSession;
import com.ecommerce.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private String baseUrl = "/v1/users";

    @Test
    public void postUserRegisterSuccess() throws Exception {
        Object userRequest = this.getResourceRequest("users/RegisterSuccess");
        doReturn(new User())
                .when(userService)
                .createUser(any());
        this.tesCurlPostController(baseUrl.concat("/register"), userRequest, "200");
    }

    @Test
    public void postUserRegisterValidateFail() throws Exception {
        Object userRequest = this.getResourceRequest("users/RegisterValidateFail");
        this.tesCurlPostController(baseUrl.concat("/register"), userRequest, "422");
    }

    @Test
    public void postUserLoginSuccess() throws Exception {
        Object userRequest = this.getResourceRequest("users/LoginSuccess");
        doReturn(new UserSession().setId(UUID.randomUUID().toString()))
                .when(userService)
                .login(any());
        this.tesCurlPostController(baseUrl.concat("/login"), userRequest, "200");
    }

    @Test
    public void postUserLoginValidateFail() throws Exception {
        Object userRequest = this.getResourceRequest("users/LoginValidateFail");
        this.tesCurlPostController(baseUrl.concat("/register"), userRequest, "422");
    }
}