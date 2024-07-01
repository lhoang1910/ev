package com.insurance.shared.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
public abstract class BaseEvent {
    protected String aggregateId;
    protected String updatedBy;

    public BaseEvent(String aggregateId, String updatedBy) {
        Objects.requireNonNull(aggregateId);
        if (aggregateId.isBlank()) throw new RuntimeException("BaseEvent aggregateId is required");
        this.aggregateId = aggregateId;
        if (updatedBy.isBlank()){
            this.updatedBy = "Annoymous";
        } else {
            this.updatedBy = updatedBy;
        }
    }
}
