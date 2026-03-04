package com.eolma.common.kafka.config;

import com.eolma.common.event.DomainEvent;
import com.eolma.common.kafka.EolmaKafkaProducer;
import com.eolma.common.kafka.KafkaEventSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name = "spring.kafka.bootstrap-servers")
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, DomainEvent<?>> producerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties(null));
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaEventSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, DomainEvent<?>> kafkaTemplate(
            ProducerFactory<String, DomainEvent<?>> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public EolmaKafkaProducer eolmaKafkaProducer(
            KafkaTemplate<String, DomainEvent<?>> kafkaTemplate) {
        return new EolmaKafkaProducer(kafkaTemplate);
    }
}
