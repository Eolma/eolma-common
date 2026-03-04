package com.eolma.common.kafka;

import com.eolma.common.event.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaEventSerializer implements Serializer<DomainEvent<?>> {

    private static final Logger log = LoggerFactory.getLogger(KafkaEventSerializer.class);
    private final ObjectMapper objectMapper;

    public KafkaEventSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(String topic, DomainEvent<?> data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            log.error("Failed to serialize DomainEvent: {}", data.id(), e);
            throw new RuntimeException("Failed to serialize DomainEvent", e);
        }
    }
}
