package com.insurance.authentication.client;

import com.insurance.authentication.dto.request.CreateUserRequest;
import com.insurance.shared.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@FeignClient(name = "command", url = "localhost:8085/internal/user")
public interface UserServiceClient {

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest command, @RequestHeader("loggedInUser") String loggedInUser);

    @GetMapping("/find/{username}")
    UserModel getUserByUsername(@PathVariable String username);

    @PostMapping("/sign-up")
    ResponseEntity<?> signUp(@Valid @RequestBody CreateUserRequest command);
}
