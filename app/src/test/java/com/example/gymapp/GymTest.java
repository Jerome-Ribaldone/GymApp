package com.example.gymapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GymTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testConstructor() {
        Gym stopwatch = new Gym();
        assertEquals("00:00:00", stopwatch.toString());
    }
    @Test
    public void testTicker() {
        Gym gym = new Gym();
        gym.tick();
        assertEquals("00:00:01", gym.toString());
        for (int i = 0; i < 59; i++) {
            gym.tick();
        }
        assertEquals("00:01:00", gym.toString());
        for (int i = 0; i < 59 * 60; i++) {
            gym.tick();
        }
        assertEquals("01:00:00", gym.toString());
    }
}