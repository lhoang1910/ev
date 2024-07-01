package com.insurance.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventStoreEntity {
    private String id;
    private String aggregateId;
    private String eventType;
    private String aggregateType;
    private long version;
    private byte[] data;
    private byte[] metaData;
    private Date timeStamp;
    private String updatedBy;


    public EventStoreEntity(String eventType, String aggregateType) {
        this.id = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.timeStamp = Date.from(Instant.now());
    }

    @Override
    public String toString() {
        return "EventStoreEntity{" +
                "id='" + id + '\'' +
                ", aggregateId='" + aggregateId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", version=" + version +
                ", data=" + Arrays.toString(data) +
                ", metaData=" + Arrays.toString(metaData) +
                ", timeStamp=" + timeStamp +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}