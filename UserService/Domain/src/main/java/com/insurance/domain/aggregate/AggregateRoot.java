package com.insurance.domain.aggregate;

import com.insurance.domain.entity.EventStoreEntity;
import com.insurance.shared.exception.InvalidEventException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public abstract class AggregateRoot {

    protected String id;
    private String type;
    private long version;
    protected String updateBy;
    private final List<EventStoreEntity> changes = new ArrayList<>();

    public AggregateRoot(final String id, final String aggregateType, final String updatedBy) {
        this.id = id;
        this.type = aggregateType;
        this.updateBy = updatedBy;
    }


    public abstract void when(final EventStoreEntity event);

    public void load(final List<EventStoreEntity> events) {
        events.forEach(event -> {
            this.validateEvent(event);
            this.raiseEvent(event);
            this.version++;
        });
    }

    public void apply(final EventStoreEntity event) {
        this.validateEvent(event);
        event.setAggregateType(this.type);

        when(event);
        changes.add(event);

        this.version++;
        event.setVersion(this.version);
    }

    public void raiseEvent(final EventStoreEntity event) {
        this.validateEvent(event);

        event.setAggregateType(this.type);
        when(event);

        this.version++;
    }

    public void clearChanges() {
        this.changes.clear();
    }

    public void toSnapshot() {
        this.clearChanges();
    }

    private void validateEvent(final EventStoreEntity event) {
        if (Objects.isNull(event) || !event.getAggregateId().equals(this.id))
            throw new InvalidEventException(event.toString());
    }

    protected EventStoreEntity createEvent(String eventType, byte[] data, byte[] metadata) {
        return EventStoreEntity.builder()
                .aggregateId(this.getId())
                .version(this.getVersion())
                .aggregateType(this.getType())
                .eventType(eventType)
                .data(Objects.isNull(data) ? new byte[]{} : data)
                .metaData(Objects.isNull(metadata) ? new byte[]{} : metadata)
                .timeStamp(Date.from(Instant.now()))
                .build();
    }

    @Override
    public String toString() {
        return "AggregateRoot{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", version=" + version +
                ", updateBy='" + updateBy + '\'' +
                ", changes=" + changes +
                '}';
    }
}