package org.company.service;

import com.google.firebase.database.*;
import org.company.config.ComponentContainer;
import org.company.entity.User;

import java.util.Arrays;

public class UserService {


    public String getByFireStoreDB(String number, Long chatId, Integer messageId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users_all/" + number);
        String[] result = {null};

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User post = dataSnapshot.getValue(User.class);
                String cashback = post.getCashback();
                result[0] = cashback;
                System.out.println("up" + Arrays.toString(result));
                ComponentContainer.TELEGRAM_BOT_SERVICE.sendResult(cashback, chatId, messageId);
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
