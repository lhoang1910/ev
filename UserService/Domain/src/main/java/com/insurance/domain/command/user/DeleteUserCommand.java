package com.insurance.domain.command.user;

public record DeleteUserCommand (String aggregateID, String isDeleted, String updatedBy){
}
