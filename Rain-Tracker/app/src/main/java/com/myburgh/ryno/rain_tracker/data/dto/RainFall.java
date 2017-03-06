package com.myburgh.ryno.rain_tracker.data.dto;

import android.location.Location;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static com.myburgh.ryno.rain_tracker.MainActivity.mLastLocation;

/**
 * Created by Ryno on 2017-03-06.
 */

public class RainFall {

    public double rainFall;

    public String date;

    public String userId;

    public long currentTimeMillis;

    public double latitude;

    public double longitude;

    public RainFall() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public RainFall(double rainFall, String date, long currentTimeMillis, String userId, double latitude,double longitude) {
        this.rainFall = rainFall;
        this.date = date;
        this.userId = userId;
        this.currentTimeMillis = currentTimeMillis;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RainFall(double rainFall, String date, long currentTimeMillis, String userId, Location location) {
        this.rainFall = rainFall;
        this.date = date;
        this.userId = userId;
        this.currentTimeMillis = currentTimeMillis;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("rainFall", rainFall);
        result.put("date", date);
        result.put("currentTimeMillis", currentTimeMillis);
        result.put("userId", userId);
        result.put("latitude", latitude);
        result.put("longitude", longitude);

        return result;
    }
}
