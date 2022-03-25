package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {

    public static final int SETTING_REQUEST = 1234;
    private EditText userInputMinutes;
    private EditText userInputSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        userInputMinutes = findViewById(R.id.userInputMinutes);
        userInputSeconds = findViewById(R.id.userInputSeconds);
    }

    public void doneClicked(View view) {
        String text =  userInputMinutes.toString();
        int setMinute = Integer.parseInt(text);
        String text2 =  userInputMinutes.toString();
        int setSecond = Integer.parseInt(text2);

        Intent intent = new Intent();
        intent.putExtra("setMinute", setMinute);
        intent.putExtra("setSecond", setSecond);
        setResult(RESULT_OK, intent);
        finish();
    }
}