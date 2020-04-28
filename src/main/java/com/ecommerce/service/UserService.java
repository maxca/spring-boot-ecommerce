package com.ecommerce.service;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.model.User;
import com.ecommerce.model.response.UserEditProfile;
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

    public UserRegister createUser(User user) throws NoSuchAlgorithmException, BusinessException {
        if (userRepository.createUser(user) == 1) {
            return new UserRegister()
                    .setEmail(user.getEmail())
                    .setName(user.getName())
                    .setPhone(user.getPhone());
        }
        throw new BusinessException(500, "can't create user");
    }

    public UserEditProfile editProfile(User user) throws NoSuchAlgorithmException, BusinessException {
        if (userRepository.editProfile(user) == 1) {
            return new UserEditProfile()
                    .setEmail(user.getEmail())
                    .setName(user.getName())
                    .setPhone(user.getPhone());
        }
        throw new BusinessException(500, "can't edit user");
    }
}
