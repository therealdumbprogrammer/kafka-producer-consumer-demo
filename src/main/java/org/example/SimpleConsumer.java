package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.event.UserSignupEvent;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class SimpleConsumer {
    public static void main(String[] args) {
        KafkaConsumer<Integer, UserSignupEvent> kafkaConsumer = startConsumer();
        kafkaConsumer.subscribe(List.of("user-signups"));

        while(true) {
           ConsumerRecords<Integer, UserSignupEvent> records = kafkaConsumer.poll(Duration.ofMillis(100));
           for (ConsumerRecord<Integer, UserSignupEvent> record : records) {
               System.out.println("Received an event: " + record.partition() + ", " + record.key() + ", " + record.value());
           }
        }
    }

    private static KafkaConsumer<Integer, UserSignupEvent> startConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.example.event.serde.UserSignupEventDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-a");

        return new KafkaConsumer<>(props);
    }
}
