package com.insurance.authentication.service;

import com.insurance.authentication.dto.request.CreateUserRequest;
import com.insurance.authentication.dto.request.LoginRequest;
import com.insurance.shared.model.UserModel;
import com.insurance.shared.response.WrapResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthService {

    ResponseEntity<?> createUser(CreateUserRequest request, String loggedInUser);

    ResponseEntity<?> signUp(CreateUserRequest request);

    WrapResponse<?> login(LoginRequest request);

    UserModel getCurrentUser(@RequestHeader("loggedInUser") String username);

}
