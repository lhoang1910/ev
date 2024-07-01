package com.insurance.command.handler;

import com.insurance.domain.command.user.CreateUserCommand;
import com.insurance.domain.command.user.DeleteUserCommand;
import com.insurance.domain.command.user.UpdateUserCommand;

public interface UserCommandHandler {
    String handle(CreateUserCommand command, String updatedBy);
    String handle(UpdateUserCommand command, String updatedBy);
    String handle(DeleteUserCommand command, String updatedBy);
}
