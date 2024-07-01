package com.insurance.authentication.client;

import com.insurance.shared.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "query", url = "localhost:8087/internal/user")
public interface UserQueryClient {
    @GetMapping("/find/{username}")
    UserModel getUserByUsername(@PathVariable String username);
}
