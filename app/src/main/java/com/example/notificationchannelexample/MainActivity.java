package com.example.notificationchannelexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import static com.example.notificationchannelexample.BaseApplication.SILENT_CHANNEL;
import static com.example.notificationchannelexample.BaseApplication.SOUND_CHHANNEL;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;
    //diff betwn notific managr,notifc compat
    private Button button_channel1;

    private EditText edit_text_title;
    private EditText edit_text_heading;
    AlarmManager alarmManager;
    private String title;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_channel1 = findViewById(R.id.button_channel1);

        edit_text_heading = findViewById(R.id.edit_text_heading);
        edit_text_title = findViewById(R.id.edit_text_title);
        notificationManagerCompat = NotificationManagerCompat.from(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        button_channel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerateAlarm();


            }


        });
    }

    private void GenerateAlarm() {
        title = edit_text_title.getText().toString();
        message = edit_text_heading.getText().toString();
        Intent intent = new Intent(this, BaseApplication.class);
        PendingIntent pd = PendingIntent.getBroadcast(this, 1, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 6000, pd);

        GenerateSilentNotification();
        GenerateSoundNotification();


    }

    private void GenerateSilentNotification() {
        Notification notification = new NotificationCompat.Builder(this, SILENT_CHANNEL)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);


    }

    private void GenerateSoundNotification() {

        Notification notification1 = new NotificationCompat.Builder(this, SOUND_CHHANNEL)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        notificationManagerCompat.notify(2, notification1);

    }


}


