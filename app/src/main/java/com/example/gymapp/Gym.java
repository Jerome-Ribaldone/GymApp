package com.example.gymapp;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Gym {
    private int minutes, seconds;

    Gym() {
        minutes = 1;
        seconds = 0;

    }

    public Gym( int minutes, int seconds) {

        this.minutes = minutes;
        this.seconds = seconds;
    }
    void tick() {
        seconds -= 1;
        if (seconds == 0) {
            minutes -= 1;
            seconds += 60;

        }
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "%02d:%02d", minutes, seconds);
    }
    public int getMinutes(){
        return minutes;
    }
    public int getSeconds(){
        return seconds;
    }
}