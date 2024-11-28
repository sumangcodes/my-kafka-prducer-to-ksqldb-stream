package com.example.my_kafka_schema_retriver.config;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

@Configuration
public class KafkaProducerConfig {
    public  Properties getProducerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka broker address
        props.put("key.serializer", KafkaAvroSerializer.class.getName()); // Serializer for the key
        props.put("value.serializer", KafkaAvroSerializer.class.getName()); // Serializer for the value
        props.put("schema.registry.url", "http://localhost:8081"); // URL of the Schema Registry

        return props;
    }
}
