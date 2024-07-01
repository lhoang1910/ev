package com.insurance.command.handler.impl;

import com.insurance.command.handler.UserCommandHandler;
import com.insurance.domain.aggregate.UserAggregate;
import com.insurance.domain.command.user.CreateUserCommand;
import com.insurance.domain.command.user.DeleteUserCommand;
import com.insurance.domain.command.user.UpdateUserCommand;
import com.insurance.domain.entity.EventStoreEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserCommandHandlerImpl implements UserCommandHandler {


    @Override
    public String handle(CreateUserCommand command,String updatedBy) {
        var aggregate = new UserAggregate(command.aggregateID(), updatedBy);
        aggregate.createUser(command.username(), command.password(), command.role(), command.userCode());
        List<EventStoreEntity> events = new ArrayList<>(aggregate.getChanges());
        if (aggregate.getVersion() > 1){

        }
        return "";
    }

    @Override
    public String handle(UpdateUserCommand command, String updatedBy) {
        return "";
    }

    @Override
    public String handle(DeleteUserCommand command,String updatedBy) {
        return "";
    }
}
