package com.eolma.common.kafka;

import com.eolma.common.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class EolmaKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(EolmaKafkaProducer.class);
    private final KafkaTemplate<String, DomainEvent<?>> kafkaTemplate;

    public EolmaKafkaProducer(KafkaTemplate<String, DomainEvent<?>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // aggregateId를 Kafka key로 사용 (같은 엔티티의 이벤트는 같은 파티션)
    public CompletableFuture<SendResult<String, DomainEvent<?>>> publish(String topic, DomainEvent<?> event) {
        log.info("Publishing event: type={}, aggregateId={}, topic={}",
                event.type(), event.aggregateId(), topic);

        return kafkaTemplate.send(topic, event.aggregateId(), event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to publish event: type={}, aggregateId={}, topic={}",
                                event.type(), event.aggregateId(), topic, ex);
                    } else {
                        log.debug("Event published: type={}, aggregateId={}, partition={}, offset={}",
                                event.type(), event.aggregateId(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });
    }
}
