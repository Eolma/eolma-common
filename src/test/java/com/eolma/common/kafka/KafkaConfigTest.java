package com.eolma.common.kafka;

import com.eolma.common.event.DomainEvent;
import com.eolma.common.event.EventType;
import com.eolma.common.event.payload.BidPlacedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaConfigTest {

    @Test
    @DisplayName("KafkaEventSerializer/Deserializer 직렬화 라운드트립")
    void serializerDeserializerRoundTrip() {
        KafkaEventSerializer serializer = new KafkaEventSerializer();
        KafkaEventDeserializer deserializer = new KafkaEventDeserializer();

        BidPlacedEvent payload = new BidPlacedEvent(
                1L, 100L, 200L, 55000L, 50000L, 3,
                LocalDateTime.of(2026, 3, 4, 12, 0, 0)
        );

        DomainEvent<BidPlacedEvent> event = DomainEvent.create(
                EventType.BID_PLACED,
                "auction-service",
                "100",
                "Auction",
                payload
        );

        // 직렬화 -> 역직렬화
        byte[] bytes = serializer.serialize("eolma.auction.events", event);
        assertThat(bytes).isNotNull();

        DomainEvent<?> deserialized = deserializer.deserialize("eolma.auction.events", bytes);
        assertThat(deserialized).isNotNull();
        assertThat(deserialized.id()).isEqualTo(event.id());
        assertThat(deserialized.type()).isEqualTo(EventType.BID_PLACED);
        assertThat(deserialized.aggregateId()).isEqualTo("100");
    }

    @Test
    @DisplayName("null 데이터 직렬화/역직렬화 시 null 반환")
    void nullHandling() {
        KafkaEventSerializer serializer = new KafkaEventSerializer();
        KafkaEventDeserializer deserializer = new KafkaEventDeserializer();

        assertThat(serializer.serialize("topic", null)).isNull();
        assertThat(deserializer.deserialize("topic", null)).isNull();
    }
}
