package org.example.event;

import java.time.LocalDateTime;

public class UserSignupEvent {
    private int userId;
    private String username;
    private LocalDateTime signupTime;

    public UserSignupEvent(int userId, String username, LocalDateTime signupTime) {
        this.userId = userId;
        this.username = username;
        this.signupTime = signupTime;
    }

    public UserSignupEvent() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(LocalDateTime signupTime) {
        this.signupTime = signupTime;
    }
}
