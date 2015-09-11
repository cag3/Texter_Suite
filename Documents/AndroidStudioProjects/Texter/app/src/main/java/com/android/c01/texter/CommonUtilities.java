package com.android.c01.texter;

import android.content.Context;
import android.content.Intent;

/**
 * Created by c01 on 8/24/2015.
 */
public final class CommonUtilities {

    // Your Server registration url goes her...
    static final  String SERVER_URL = "dev.intoweb.com/gcm_server/register.php";

    // Google project id here
    static final String SENDER_ID = "texter-1042";

    // Tag for log messages..
    static final String TAG = "GCM Android";

    //
    static  final String DISPLAY_MESSAGE_ACTION = "com.android.c01.texter.DISPLAY_MESSAGE";

    static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies the UI to display a message...
     */
    static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }


}
