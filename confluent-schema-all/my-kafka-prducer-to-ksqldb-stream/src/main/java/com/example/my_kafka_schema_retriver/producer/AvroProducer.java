package com.example.my_kafka_schema_retriver.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.my_kafka_schema_retriver.config.KafkaProducerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AvroProducer {
    @Autowired
    KafkaProducerConfig config;

    public void sendEvent() {
        Properties props = config.getProducerProperties();
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // Create and send records to the carLocations topic
        sendCarLocation(producer, "Car A2", "Red", true, "0 1");
        sendCarLocation(producer, "Car A2", "Red", true, "0 2");
        sendCarLocation(producer, "Car B2", "BLUE", false, "5 3");
        sendCarLocation(producer, "Car A2", "Red", true, "1 2");

        producer.close();
    }

    private void sendCarLocation(KafkaProducer<String, String> producer,
            String profileId, String color, boolean hasPapers, String location) {
        // Create a JSON object
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRecord = null;
        try {
            jsonRecord = objectMapper.writeValueAsString(new CarLocation(profileId, color, hasPapers, location));
        } catch (Exception e) {
            System.err.println("Error creating JSON record: " + e.getMessage());
            return;
        }

        // Send the record to the carLocations topic
        ProducerRecord<String, String> record = new ProducerRecord<>("carLocations", profileId, jsonRecord);

        try {
            producer.send(record);
        } catch (SerializationException e) {
            System.err.println("Serialization exception: " + e.getMessage());
        }
    }

    private static class CarLocation {
        public String profileId;
        public String color;
        public boolean hasPapers;
        public String location;

        public CarLocation(String profileId, String color, boolean hasPapers, String location) {
            this.profileId = profileId;
            this.color = color;
            this.hasPapers = hasPapers;
            this.location = location;
        }
    }
}
