package com.hrankit.notifications24hours;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.hrankit.notifications24hours.MainActivity.NOTIFICATION_DATA;
import static com.hrankit.notifications24hours.MainActivity.NOTIFICATION_TITLE;

public class NotificationReceiver extends BroadcastReceiver {

    private String Title = "NotificationApp";
    private String Data = "Default Data is being shown as intent was null or missing";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper notificationHelper = new NotificationHelper(context);

        if (intent.getExtras() != null && intent.hasExtra(NOTIFICATION_TITLE) && intent.hasExtra(NOTIFICATION_DATA)) {
            Title = intent.getExtras().getString(NOTIFICATION_TITLE);
            Data = intent.getExtras().getString(NOTIFICATION_DATA);

        }

        if (Title != null && Data != null) {
            notificationHelper.createNotification(Title, Data);
        }
    }
}
