package com.example.notificationchannelexample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class BaseApplication extends Application {
//    public static final String CHANNEL_1_ID = "channel1";
//    public static final String CHANNEL_2_ID = "channel2";
    public static final String SILENT_CHANNEL="Silent notification";
    public static final String SOUND_CHHANNEL="Sound notification";


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        //for API level 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(SILENT_CHANNEL, "Silent notification",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("this is channel1");

            NotificationChannel channel2 = new NotificationChannel(SOUND_CHHANNEL, "Sound notification",
                    NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("This is channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            //to read diffrence between createnotificationchannel and
            // create notification channnels.


        }
    }


}
