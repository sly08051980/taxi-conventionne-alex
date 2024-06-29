package com.slyfly1.taxiconventionne77.calendrier;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import com.slyfly1.taxiconventionne77.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CalendarNotificationChannel extends Application {
    public static final String CHANNEL_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser !=null){


            Intent i = new Intent(CalendarNotificationChannel.this, MainActivity.class);
            i.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK); startActivity (i);
        }
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_ID,
                    "Event notifications",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setSound(null, null);
            channel1.setDescription("Notifications which remind the event before it's happened.");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);

        }
    }
}
