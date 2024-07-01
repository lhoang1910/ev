package com.insurance.authentication.controller;

import com.insurance.authentication.service.AuthService;
import com.insurance.shared.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/api")
public class InternalApi {

    @Autowired
    private AuthService authService;

    @GetMapping("/curren-user")
    public UserModel getCurrentUser(@RequestHeader("loggedInUser") String username) {
        return authService.getCurrentUser(username);
    }
}
