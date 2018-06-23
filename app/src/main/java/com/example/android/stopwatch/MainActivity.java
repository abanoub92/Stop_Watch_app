package com.example.android.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /*variable storage increasing seconds with 1 second*/
    int seconds = 0;

    /*variable check if the counter is running or not running*/
    boolean isRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRun = savedInstanceState.getBoolean("isRun");
        }

        /*method counter to run the counter of stop watch*/
        counter();
    }

    /**
     *this method save the state of the counter if the orientation is changed
     * @param savedInstanceState set the state of the counter variables
     */
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRun", isRun);
    }

    /**
     * Reset the counter to beginning (00:00:00)
     * @param view connecting with xml view button
     */
    public void resetCounter(View view){
        /*The counter running is false*/
        isRun = false;
        /*The counter running is returning to default condition*/
        seconds = 0;
    }

    /**
     * Start working the counter
     * @param view connecting with xml view button
     */
    public void startCounter(View view){
        /*The counter running is true*/
        isRun = true;
    }

    /**
     * Stop the counter of working
     * @param view connecting with xml view button
     */
    public void stopCounter(View view){
        /*The counter running is false*/
        isRun = false;
    }

    /**
     * This method handle with the counter and running it
     * depend on isRun boolean variable
     */
    private void counter(){
        final TextView timer = findViewById(R.id.timer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String counter = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);

                timer.setText(counter);

                if (isRun){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

}
