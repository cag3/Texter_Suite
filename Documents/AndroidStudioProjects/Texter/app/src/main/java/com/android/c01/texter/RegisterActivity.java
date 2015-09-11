package com.android.c01.texter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.android.c01.texter.CommonUtilities.SENDER_ID;
import static com.android.c01.texter.CommonUtilities.SERVER_URL;

/**
 * Created by c01 on 8/24/2015.
 */
public class RegisterActivity extends Activity {

    AlertDialogManager alert = new AlertDialogManager();

    ConnectionDetector cd;

    EditText txtUsername;
    EditText txtEmail;

    Button BtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cd = new ConnectionDetector(getApplicationContext());

       /* if (!cd.isConnectingToInternet()) {
            alert.showAlertDialog(RegisterActivity.this, "Internet connection has an Error", "Please connect to a working internet connection", false);
            Toast.makeText(getBaseContext(), "cd: "+cd, Toast.LENGTH_LONG).show();
            return;
        }*/
        if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0 || SENDER_ID.length() == 0){
            alert.showAlertDialog(RegisterActivity.this, "Configuration error", "Please set your Server URL and GCM Sender ID", false);
            Toast.makeText(getBaseContext(), "server url: "+SERVER_URL+" sender id: "+SENDER_ID, Toast.LENGTH_LONG).show();
            return;
        }

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        BtnRegister = (Button) findViewById(R.id.btnRegister);

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                Toast.makeText(getBaseContext(), "Text username: "+username+"text email: "+email, Toast.LENGTH_LONG).show();
                if (username.trim().length() > 0 && email.trim().length() > 0)
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                }else{
                    alert.showAlertDialog(RegisterActivity.this, "Registration failed", "Please enter correct details", false);
                }

            }
        });
    }
}
