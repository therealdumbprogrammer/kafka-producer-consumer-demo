package org.example.event.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.event.UserSignupEvent;

public class UserSignupEventSerializer implements Serializer<UserSignupEvent> {
    private ObjectMapper objectMapper;

    public UserSignupEventSerializer() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public byte[] serialize(String topic, UserSignupEvent userSignupEvent) {
        try {
            return objectMapper.writeValueAsBytes(userSignupEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
