package com.company.service;

import com.company.config.ComponentContainer;
import com.company.entity.User;
import com.google.firebase.database.*;

import java.util.Arrays;

public class UserService {


    public String getByFireStoreDB(String number, Long chatId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users_all/" + number);
        String[] result = {null};

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User post = dataSnapshot.getValue(User.class);
                String cashback = post.getCashback();
                result[0] = cashback;
                ComponentContainer.TELEGRAM_BOT_SERVICE.sendResult(cashback, chatId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });

        System.out.println("down" + Arrays.toString(result));
        return result[0];
    }

}
