package com.ecommerce.controller;

import com.ecommerce.model.User;
import com.ecommerce.model.UserSession;
import com.ecommerce.model.request.UserLoginRequest;
import com.ecommerce.model.response.ResponseModel;
import com.ecommerce.model.response.UserEditProfile;
import com.ecommerce.model.response.UserRegister;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user) throws NoSuchAlgorithmException {
        UserRegister userRegister = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(userRegister));
    }

    @PutMapping("/edit-profile")
    public ResponseEntity<ResponseModel> editprofile(@RequestBody @Valid User user) throws NoSuchAlgorithmException {
        UserEditProfile userEditProfile = userService.editProfile(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(userEditProfile));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginRequest user) {
        UserSession userSession = userService.login(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel(userSession));

    }

}
