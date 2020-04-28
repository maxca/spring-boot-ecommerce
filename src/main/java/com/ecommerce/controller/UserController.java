package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

import static com.ecommerce.helper.EncryptionHelper.md5;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user) throws NoSuchAlgorithmException {
        User profile = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(profile));
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<ResponseModel> editProfile(@RequestBody @Valid User user) throws NoSuchAlgorithmException {
        User userEditProfile = userService.editProfile(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(userEditProfile));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest user) {
        UserSession userSession = userService.login(user);
        log.info(md5("test"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(userSession));

    }

}
