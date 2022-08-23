package com.example.fcmdemo;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.microsoft.windowsazure.messaging.notificationhubs.NotificationListener;

import java.util.Map;

public class CustomNotificationListener implements NotificationListener {
    public String TAG = "CustomNotificationListener";

    @Override
    public void onPushNotificationReceived(Context context, RemoteMessage message) {
        /* The following notification properties are available. */
        RemoteMessage.Notification notification = message.getNotification();
        assert notification != null;
        String title = notification.getTitle();
        String body = notification.getBody();

        Map<String, String> data = message.getData();
        Log.d(TAG, "onPushNotificationReceived:" +data.toString());


        if (message != null) {
            Log.e(TAG, "Message Notification Title: " + title);
            Log.e(TAG, "Message Notification Body: " + body);
        }

        if (data != null) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                Log.e(TAG, "key, " + entry.getKey() + " value " + entry.getValue());
            }
        }
    }


}