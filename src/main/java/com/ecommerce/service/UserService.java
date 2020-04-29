package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.RecordNotFoundException;
import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.User;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.request.SessionIdRequest;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionService userSessionService;

    public User createUser(User user) throws BusinessException {
        String userId = userRepository.createUser(user);
        if (null == userId) {
            throw new BusinessException(500, "can't create user");
        }
        User profile = userRepository.findUserById(userId);
        if (null == profile) {
            throw new RecordNotFoundException(404, "not found user");
        }
        return profile;
    }

    public User editProfile(User user) throws BusinessException {
        if (null != userRepository.editProfile(user)) {
            return userRepository.findUserById(user.getId());
        }
        throw new BusinessException(500, "can't edit user");
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

    public Object logout(String request) throws BusinessException {
        String profile = userSessionService.deleteUserSession(request);
        if (null == profile) {
            throw new UnauthorizedException(401, "Unauthorized service");
        }
        return null;
    }
}
