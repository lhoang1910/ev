package com.insurance.domain.command.user;

public record UpdateUserCommand (String aggregateID, String username, String password, String role, String updatedBy){

}
