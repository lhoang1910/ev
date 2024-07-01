package com.insurance.domain.command;

import com.demo.shared.base.BaseCommand;

public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
