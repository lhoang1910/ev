package com.insurance.domain.aggregate;

import com.insurance.domain.entity.EventStoreEntity;
import com.insurance.domain.event.UserCreatedEvent;
import com.insurance.domain.event.UserDeletedEvent;
import com.insurance.domain.event.UserUpdatedEvent;
import com.insurance.domain.utils.SerializerUtils;
import com.insurance.shared.exception.InvalidEventTypeException;
import lombok.*;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserAggregate extends AggregateRoot {

    public static final String AGGREGATE_TYPE = "UserAggregate";

    public UserAggregate(String id, String updatedBy){
        super(id, AGGREGATE_TYPE, updatedBy);
    }

    private String userCode;
    private String username;
    private String password;
    private int role;
    private Boolean isDeleted;

    @Override
    public void when(EventStoreEntity event) {
        switch (event.getEventType()) {
            case UserCreatedEvent.USER_CREATED_EVENT_V1 ->
                    handle(SerializerUtils.deserializeFromJsonBytes(event.getData(), UserCreatedEvent.class));
            case UserUpdatedEvent.USER_UPDATED_EVENT_V1 ->
                    handle(SerializerUtils.deserializeFromJsonBytes(event.getData(), UserUpdatedEvent.class));
            case UserDeletedEvent.USER_WAS_DELETED ->
                    handle(SerializerUtils.deserializeFromJsonBytes(event.getData(), UserDeletedEvent.class));
            default -> throw new InvalidEventTypeException(event.getEventType());
        }
    }

    public void handle(UserCreatedEvent userCreatedEvent){
        this.userCode = userCreatedEvent.getUserCode();
        this.username = userCreatedEvent.getUsername();
        this.password = userCreatedEvent.getPassword();
        this.role = userCreatedEvent.getRole();
        this.isDeleted = userCreatedEvent.getIsDeleted();
    }

    public void handle(UserUpdatedEvent userUpdatedEvent){
        this.username = userUpdatedEvent.getNewUsername();
        this.password = userUpdatedEvent.getNewPassword();
        this.role = userUpdatedEvent.getNewRole();
    }

    public void handle(UserDeletedEvent userDeletedEvent){
        this.isDeleted = true;
    }

    public void createUser(String username, String password, int role, String userCode){
        var data = UserCreatedEvent.builder()
                .aggregateId(id)
                .userCode(userCode)
                .username(username)
                .password(password)
                .updatedBy(updateBy)
                .role(role)
                .build();

        byte[] dataBytes = SerializerUtils.serializeToJsonBytes(data);
        var event = this.createEvent(UserCreatedEvent.USER_CREATED_EVENT_V1, dataBytes, null);
        apply(event);
    }

    public void updateUser(String username, String password, int role, String userCode){
        var data = UserUpdatedEvent.builder()
                .aggregateId(id)
                .updatedBy(updateBy)
                .newUsername(username)
                .newPassword(password)
                .newRole(role)
                .build();
    }

    public void deleteUser(){
        var data = UserDeletedEvent.builder()
                .aggregateId(id)
                .isDeleted(true)
                .updatedBy(updateBy)
                .build();
    }

}
