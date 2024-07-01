package com.insurance.domain.command.user;

public record CreateUserCommand (String aggregateID, String userCode,String username, String password, int role, String updatedBy) {

}
