package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.User;
import com.ecommerce.model.response.UserEditProfile;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.model.response.UserRegister;
import com.ecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionService userSessionService;

    public UserRegister createUser(User user) throws NoSuchAlgorithmException, BusinessException {
        if (userRepository.createUser(user) == 1) {
            return new UserRegister()
                    .setEmail(user.getEmail())
                    .setName(user.getName())
                    .setPhone(user.getPhone());
        }
        throw new BusinessException(500, "can't create user");
    }

    public UserEditProfile editProfile(User user) {
        return new UserEditProfile()
                .setEmail(user.getEmail())
                .setName(user.getName())
                .setPhone(user.getPhone());
    }

    public UserSession login(UserLoginRequest request) {
        // check login with user and password
        User profile = userRepository.login(request);
        if (null == profile) {
            throw new UnauthorizedException(401, "Unauthorized service");
        }
        // update last login
        if (null == userRepository.updateLastLogin(profile.getId())) {
            throw new BusinessException(500, "Update Last login fail");
        }
        // create session Id
        return userSessionService.createUserSession(profile.getId());
    }
}
