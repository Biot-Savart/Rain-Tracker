package com.myburgh.ryno.rain_tracker.data;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.myburgh.ryno.rain_tracker.data.dto.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Ryno on 2017-03-02.
 */

public class RainTrackerDatabase {

    private DatabaseReference mDatabase;

    public RainTrackerDatabase()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        String key = mDatabase.child("users").push().getKey();

        HashMap<String, Object> timestamp = new HashMap<>();
        timestamp.put("timestamp", System.currentTimeMillis());

        Map<String, Object> postValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + userId, postValues);
          childUpdates.put("/user-logins/" + userId + "/" + key, timestamp);

        mDatabase.updateChildren(childUpdates);

        DatabaseReference userRef = mDatabase.getDatabase().getReference("users");

        userRef.orderByValue().addChildEventListener(new ChildEventListener() {
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


        DatabaseReference userloginRef = mDatabase.getDatabase().getReference("user-logins");
        userloginRef.orderByValue().addChildEventListener(new ChildEventListener() {
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

       // mDatabase.child("users").child(userId).setValue(user);
    }

}
