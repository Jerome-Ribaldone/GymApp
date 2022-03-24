package com.example.gymapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GymActivity extends AppCompatActivity {
    private Gym gym;
    private Handler handler;
    private boolean isRunning;
    private TextView display;
    private TextView repDisplay;
    private Button toggle;

    private int rep;
    private int speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display1);
        repDisplay = findViewById(R.id.rep);
        toggle = findViewById(R.id.toggle);


        isRunning = false;
        if (savedInstanceState == null) {
            gym = new Gym();
            speed = 1000;
        } else {
            int minutes = savedInstanceState.getInt("minutes");
            int seconds = savedInstanceState.getInt("seconds");
            gym = new Gym(minutes, seconds);
            display.setText(gym.toString());
            boolean running = savedInstanceState.getBoolean("isRunning");
            speed = savedInstanceState.getInt("speed");
            if (running) {
                enableStopwatch();
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("minutes", gym.getMinutes());
        outState.putInt("seconds", gym.getSeconds());
        outState.putBoolean("isRunning", isRunning);
        outState.putInt("Speed", speed);
    }


    public void toggleClicked(View view) {
        System.out.println("toggleClicked");
        if (isRunning){
            disableStopwatch();
        }
        else{
            enableStopwatch();
        }
    }
    public void settingsClicked(View view) {
        Intent intent = new Intent(this, SettingActivity.class);

        startActivityForResult(intent, SettingActivity.SETTING_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == SettingActivity.SETTING_REQUEST) {
            if (resultCode == RESULT_OK){
                if (data != null){
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

    private void enableStopwatch() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                if (isRunning){
                    gym.tick();
                    display.setText(gym.toString());
                    handler.postDelayed(this, speed);
                }
            }
        });
    }
    private void disableStopwatch() {
        isRunning = false;
    }

    public void repAdd(View view) {
        Button button = (Button) view;

        rep += 1;
        displayRep();
    }

    public void clearRep(View view) {
        rep = 0;
        displayRep();
    }

    private void displayRep() {
        String result = "" + rep;
        repDisplay.setText(result);
    }

}