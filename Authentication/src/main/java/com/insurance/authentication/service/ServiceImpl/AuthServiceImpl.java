package com.insurance.authentication.service.ServiceImpl;

import com.insurance.authentication.client.UserServiceClient;
import com.insurance.authentication.dto.request.CreateUserRequest;
import com.insurance.authentication.dto.request.LoginRequest;
import com.insurance.authentication.service.AuthService;
import com.insurance.authentication.utils.GenerateJwt;
import com.insurance.shared.model.UserModel;
import com.insurance.shared.response.WrapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GenerateJwt generateJwt;

    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public ResponseEntity<?> createUser(CreateUserRequest request, String loggedInUser) {
        return userServiceClient.createUser(request, loggedInUser);
    }

    @Override
    public ResponseEntity<?> signUp(CreateUserRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userServiceClient.signUp(request);
    }

    @Override
    public WrapResponse<?> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            String jwtToken = generateJwt.generateToken(request.getUsername());
            return new WrapResponse<>(true, HttpStatus.OK.value(), "Đăng nhập thành công", jwtToken);
        }
        return new WrapResponse<>(true, HttpStatus.UNAUTHORIZED.value(), "Tài khoản hoặc mật khẩu không chính xác");
    }

    @Override
    public UserModel getCurrentUser(@RequestHeader("loggedInUser") String username) {
        var user = userServiceClient.getUserByUsername(username);

        if (user == null) {
            return UserModel.builder()
                    .username("Annoymous user")
                    .build();
        }

        return UserModel.builder()
                .id(user.getId())
                .userCode(user.getUserCode())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .deleted(user.isDeleted())
                .build();
    }
}
