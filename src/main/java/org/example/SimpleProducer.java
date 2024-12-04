package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.event.UserSignupEvent;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

public class SimpleProducer {
    private KafkaProducer<Integer, UserSignupEvent> kafkaProducer;

    public static void main(String[] args) {
        SimpleProducer simpleProducer = new SimpleProducer();
        simpleProducer.startProducer();

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the portal!");
        while(true) {
            System.out.println("Enter the userID:");
            int id = sc.nextInt();

            System.out.println("Enter the username:");
            String username = sc.next();

            simpleProducer.generateEvent(new UserSignupEvent(id, username, LocalDateTime.now()));

            System.out.println("Type 'c' to continue and 'q' to quit");
            String choice = sc.next();
            if("q".equalsIgnoreCase(choice)) {
                System.out.println("Exiting...");
                return;
            }
        }
    }

    private void generateEvent(UserSignupEvent userSignupEvent) {
        kafkaProducer.send(new ProducerRecord<>("user-signups", userSignupEvent.getUserId(), userSignupEvent));
        System.out.println("Event sent!");
    }

    private void startProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.event.serde.UserSignupEventSerializer");

        kafkaProducer = new KafkaProducer<>(props);


    }
}
