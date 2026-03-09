package com.eolma.common.event;

import com.eolma.common.id.TsidGenerator;

import java.time.LocalDateTime;

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
                TsidGenerator.generate(),
                type,
                source,
                LocalDateTime.now(),
                aggregateId,
                aggregateType,
                payload
        );
    }
}
