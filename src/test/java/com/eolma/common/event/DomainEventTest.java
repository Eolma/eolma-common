package com.eolma.common.event;

import com.eolma.common.event.payload.ProductActivatedEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DomainEventTest {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Test
    @DisplayName("DomainEvent 생성 시 id, occurredAt 자동 설정")
    void createDomainEvent() {
        ProductActivatedEvent payload = new ProductActivatedEvent(
                1L, 100L, "Test Product", "Description", "ELECTRONICS",
                "A", 10000L, 50000L, 30000L, 1000L, "TIME", "24h",
                List.of("https://example.com/img1.jpg")
        );

        DomainEvent<ProductActivatedEvent> event = DomainEvent.create(
                EventType.PRODUCT_ACTIVATED,
                "product-service",
                "1",
                "Product",
                payload
        );

        assertThat(event.id()).isNotNull();
        assertThat(event.type()).isEqualTo(EventType.PRODUCT_ACTIVATED);
        assertThat(event.source()).isEqualTo("product-service");
        assertThat(event.occurredAt()).isNotNull();
        assertThat(event.aggregateId()).isEqualTo("1");
        assertThat(event.aggregateType()).isEqualTo("Product");
        assertThat(event.payload().productId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("DomainEvent JSON 직렬화/역직렬화")
    void serializeAndDeserialize() throws Exception {
        ProductActivatedEvent payload = new ProductActivatedEvent(
                1L, 100L, "Test Product", "Description", "ELECTRONICS",
                "A", 10000L, 50000L, 30000L, 1000L, "TIME", "24h",
                List.of("https://example.com/img1.jpg")
        );

        DomainEvent<ProductActivatedEvent> event = DomainEvent.create(
                EventType.PRODUCT_ACTIVATED,
                "product-service",
                "1",
                "Product",
                payload
        );

        // 직렬화
        String json = objectMapper.writeValueAsString(event);
        assertThat(json).contains("product.activated");
        assertThat(json).contains("Test Product");

        // 역직렬화
        DomainEvent<?> deserialized = objectMapper.readValue(json,
                new TypeReference<DomainEvent<?>>() {});
        assertThat(deserialized.id()).isEqualTo(event.id());
        assertThat(deserialized.type()).isEqualTo(EventType.PRODUCT_ACTIVATED);
        assertThat(deserialized.source()).isEqualTo("product-service");
        assertThat(deserialized.aggregateId()).isEqualTo("1");
    }
}
