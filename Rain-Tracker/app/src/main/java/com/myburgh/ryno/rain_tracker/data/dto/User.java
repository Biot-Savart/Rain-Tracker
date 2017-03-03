package com.myburgh.ryno.rain_tracker.data.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ryno on 2017-03-02.
 */

public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("email", email);

        return result;
    }
}
