package com.myburgh.ryno.rain_tracker.data;

import android.location.Location;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.myburgh.ryno.rain_tracker.data.dto.RainFall;
import com.myburgh.ryno.rain_tracker.data.dto.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.myburgh.ryno.rain_tracker.MainActivity.googleAccount;

/**
 * Created by Ryno on 2017-03-02.
 */

public class RainTrackerDatabase {

    private DatabaseReference mDatabase;

    public RainTrackerDatabase()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void userLogin(String userId, String name, String email) {
        User user = new User(name, email);

        String key = mDatabase.child("users").push().getKey();

        HashMap<String, Object> timestamp = new HashMap<>();
        timestamp.put("timestamp", System.currentTimeMillis());

        Map<String, Object> postValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + userId, postValues);
        childUpdates.put("/user-logins/" + userId + "/" + key, timestamp);

        mDatabase.updateChildren(childUpdates);

       // mDatabase.child("users").child(userId).setValue(user);
    }

    public void logRain(double rainfallmm, Location location)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new GregorianCalendar().getTime();
        String shortDate = format.format(date);


        RainFall rainFall = new RainFall(rainfallmm,shortDate,date.getTime(),googleAccount.getId(),location);

        String key = mDatabase.child("rainfall").push().getKey();

        Map<String, Object> postValues = rainFall.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/rainfall/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    public void getUserLogins(String userId, int count)
    {
        DatabaseReference userloginRef = mDatabase.getDatabase().getReference("user-logins").child(userId);
        userloginRef.orderByValue().limitToLast(5).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Object se = dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getUsers()
    {
        DatabaseReference userRef = mDatabase.getDatabase().getReference("users");

        userRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User se = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User se = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
