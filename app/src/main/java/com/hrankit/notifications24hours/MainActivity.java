package com.hrankit.notifications24hours;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    //    Use any of this below.
    private static final long INTERVAL_FIFTEEN_MINUTES = 15 * 60 * 1000;
    private static final long INTERVAL_EVERYDAY = 24 * 60 * 1000;
    private static final long INTERVAL_EVERYMINUTE = 60 * 1000;
    private static final long INTERVAL_EVERY_FIVE_MINUTE = 5 * 60 * 1000;
    private static final long INTERVAL_EVERY_TEN_SECONDS = 10 * 1000; // Just for trial. It works pre-lollipop.
    public static String NOTIFICATION_TITLE = "title";
    public static String NOTIFICATION_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {

        Calendar calendar = Calendar.getInstance();
        int currentHourIn24Format = calendar.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)
        int currentMinuteIn24Format = calendar.get(Calendar.MINUTE); // return the minute


        calendar.set(Calendar.HOUR_OF_DAY, 17); // Change to your time of seeing this.
        calendar.set(Calendar.MINUTE, 8); // Change to your time, add 1 min to the time now.
        if (calendar.getTime().compareTo(new Date()) < 0) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        intent.putExtra(NOTIFICATION_TITLE, "Hour of the Day is: " + currentHourIn24Format);
        intent.putExtra(NOTIFICATION_DATA, "Hour of the Day is: " + currentMinuteIn24Format + " when the alarm was set.");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            /*
        Change the value below i.e. INTERVAL_EVERYMINUTE to your choice.
        TO make it everyday just change INTERVAL_EVERYMINUTE to INTERVAL_EVERYDAY */
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL_EVERYMINUTE, pendingIntent);
        }


    }
}
