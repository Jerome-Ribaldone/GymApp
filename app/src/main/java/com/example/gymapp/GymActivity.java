package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display1);
        toggle = findViewById(R.id.toggle);

        isRunning = false;
        if (savedInstanceState == null) {
            gym = new Gym();
        } else {
            int minutes = savedInstanceState.getInt("minutes");
            int seconds = savedInstanceState.getInt("seconds");
            gym = new Gym(minutes, seconds);
            display.setText(gym.toString());
            boolean running = savedInstanceState.getBoolean("isRunning");
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

    private void enableStopwatch() {
        isRunning = true;
        handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                if (isRunning){
                    gym.tick();
                    display.setText(gym.toString());
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
    private void disableStopwatch() {
        isRunning = false;
    }

}