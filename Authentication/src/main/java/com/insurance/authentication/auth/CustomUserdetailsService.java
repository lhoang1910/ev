package com.insurance.authentication.auth;

import com.insurance.authentication.client.UserQueryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;

@Component
public class CustomUserdetailsService implements UserDetailsService {

    @Autowired
    private UserQueryClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        var user = userServiceClient.getUserByUsername(username);
        return new UserDetail(user);
    }
}
