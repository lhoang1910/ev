package com.insurance.domain.event;

import com.insurance.domain.aggregate.UserAggregate;
import com.insurance.shared.base.BaseEvent;
import lombok.Builder;
import lombok.Data;


@Data
public class UserDeletedEvent extends BaseEvent {
    public static final String USER_WAS_DELETED = "User_WAS_DELETED";
    public static final String AGGREGATE_TYPE = UserAggregate.AGGREGATE_TYPE;

    private Boolean isDeleted;

    @Builder
    public UserDeletedEvent(String aggregateId, Boolean isDeleted, String updatedBy) {
        super(aggregateId, updatedBy);
        this.isDeleted = isDeleted;
    }
}
