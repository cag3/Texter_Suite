package com.android.c01.texter;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import com.google.android.gcm.GCMBaseIntentService;

import static com.android.c01.texter.CommonUtilities.SENDER_ID;
import static com.android.c01.texter.CommonUtilities.displayMessage;

/**
 * Created by c01 on 8/25/2015.
 */
public class GCMIntentService extends GCMBaseIntentService {

    private static final String TAG = ("GCMIntentService");
    ConnectionDetector cd;
    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {

        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("price");
        displayMessage(context, message);
        generateNotification(context, message);
    }

    @Override
    protected void onError(Context context, String  errorId) {
        Log.i(TAG, "Received error" + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registered with GCM");
        Log.i("USERNAME", MainActivity.username);
        ServerUtilities.register(context, MainActivity.username, MainActivity.email, registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        Log.i(TAG, "Received recoverable error " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error, errorId));
        return super.onRecoverableError(context, errorId);
    }

    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted message notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        generateNotification(context, message);
    }
    private void generateNotification(Context context, String message) {
        int icon  = R.mipmap.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        final int sdkVersion = Integer.parseInt(Build.VERSION.SDK);

        if (sdkVersion < Build.VERSION_CODES.HONEYCOMB)
        {
            Notification notification = new Notification(icon, message, when);

            String title = context.getString(R.string.app_name);
            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
            notification.setLatestEventInfo(context, title, message, intent);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            // Play default notification sound
            notification.defaults |= Notification.DEFAULT_SOUND;

            // Vibrate if vibrate is enabled
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(0, notification);
        }
        else {
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("New mail")
                    .setContentText(message)
                    .setSmallIcon(icon)
                    .build();

            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            // Play default notification sound
            notification.defaults |= Notification.DEFAULT_SOUND;

            // Vibrate if vibrate is enabled
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(0, notification);
        }
    }
}



