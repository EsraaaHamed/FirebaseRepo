package com.example.firebasedemo.Screens.PushNotificationScreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebasedemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class PushNotificationActivity extends AppCompatActivity {
public static final String TAG="tokens";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        /*String msg = getString(R.string.msg_token_fmt, token);*/
                        Log.i(TAG, token);
                        Toast.makeText(PushNotificationActivity.this, token, Toast.LENGTH_SHORT).show();
                        //f0bWrcBwDwc:APA91bEphcajPxlO9fHQlmoZ9e-YY7JSP4GOvAfS5G4zjLbkpIPJbz9fELMK8MSU1G_jIJP7AkIVWU2M8U3uZovtTYvhztGZKKCDUvEIPO-fdG_Y7uhnDe6UplKJXokvRX5dWHYKoKUe
                    }
                });
    }
}
