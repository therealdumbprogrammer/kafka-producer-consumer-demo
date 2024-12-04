package org.example.event.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.event.UserSignupEvent;

import java.io.IOException;

public class UserSignupEventDeserializer implements Deserializer<UserSignupEvent> {
    private ObjectMapper objectMapper;

    public UserSignupEventDeserializer() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Override
    public UserSignupEvent deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, UserSignupEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
