package com.eolma.common.kafka;

import com.eolma.common.event.DomainEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaEventDeserializer implements Deserializer<DomainEvent<?>> {

    private static final Logger log = LoggerFactory.getLogger(KafkaEventDeserializer.class);
    private final ObjectMapper objectMapper;

    public KafkaEventDeserializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public DomainEvent<?> deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, new TypeReference<DomainEvent<?>>() {});
        } catch (Exception e) {
            log.error("Failed to deserialize DomainEvent from topic: {}", topic, e);
            throw new RuntimeException("Failed to deserialize DomainEvent", e);
        }
    }
}
