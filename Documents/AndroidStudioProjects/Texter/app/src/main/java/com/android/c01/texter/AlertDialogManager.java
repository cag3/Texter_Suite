package com.android.c01.texter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by c01 on 8/24/2015.
 */
public class AlertDialogManager {

    public void showAlertDialog(Context context, String title, String message, Boolean status)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status != null)

            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       // Shows the dialog
        alertDialog.show();
    }
}
