package com.eolma.common.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record DomainEvent<T>(
        String id,
        String type,
        String source,
        LocalDateTime occurredAt,
        String aggregateId,
        String aggregateType,
        T payload
) {

    public static <T> DomainEvent<T> create(
            String type,
            String source,
            String aggregateId,
            String aggregateType,
            T payload
    ) {
        return new DomainEvent<>(
                UUID.randomUUID().toString(),
                type,
                source,
                LocalDateTime.now(),
                aggregateId,
                aggregateType,
                payload
        );
    }
}
