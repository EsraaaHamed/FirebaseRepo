package com.example.firebasedemo.Screens.HomeScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebasedemo.Screens.LoginRegisterScreen.LoginRegister;
import com.example.firebasedemo.R;
import com.example.firebasedemo.Screens.NotesListScreen.ReadingWritingToRealDb;
import com.example.firebasedemo.Screens.PushNotificationScreen.PushNotificationActivity;

public class MainActivity extends AppCompatActivity {
    Button loginRegisterBtn;
    Button pushNotificationDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginRegisterBtn=(Button)findViewById(R.id.loginRegisterBtn);
        pushNotificationDemo=(Button)findViewById(R.id.pushNotificationDemo);
        loginRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginRegister.class);
                startActivity(intent);

            }
        });
        pushNotificationDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PushNotificationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void readingFromDataBaseDemo(View view) {
        Intent Dbintent = new Intent(this, ReadingWritingToRealDb.class);
        startActivity(Dbintent);

    }
}
