package com.insurance.shared.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private String id;
    private String userCode;
    private String username;
    private String password;
    private int role;
    private boolean deleted;
}
