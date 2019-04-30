package com.rebootapp.rebootapp2;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView textView,message;
CountDownTimer mCountDownTimer;
MediaPlayer mediaPlayer;


    //function to play on the media player
    public void playMe(View view){
        mediaPlayer.start();;
    }

    //pause the media player
    public void pauseMe(View view){
        mediaPlayer.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.timer);
message= findViewById(R.id.messsage);
        mediaPlayer = MediaPlayer.create(this, R.raw.rain);



    }

    public void runtimer(View view) {
        switch (view.getId()){
            case R.id.min3:
                mediaPlayer.start();
                setmCountDownTimer(10000);

                break;

            case R.id.min5:
                mediaPlayer.start();
                setmCountDownTimer(300000);
                break;

            case R.id.min10:
                mediaPlayer.start();
                setmCountDownTimer(600000);
                break;
        }



    }

    public void setmCountDownTimer(long t){
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if(mediaPlayer.isPlaying()){
//            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
//            Toast.makeText(this, "Muted", Toast.LENGTH_SHORT).show();
        }else{
            mediaPlayer.start();
        }

        mCountDownTimer = new CountDownTimer(t, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("" + (millisUntilFinished / 1000)/60 + ":" + (millisUntilFinished/1000)%60);
                message.setText("Just sit back, relax and breathe.");
            }

            @Override
            public void onFinish() {
                message.setText("Done");
textView.setText("0:00");
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.music1:
                Toast.makeText(this, "music1", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.music2:
                Toast.makeText(this, "music2", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.mute:

                if (mCountDownTimer != null) {


                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        item.setIcon(R.drawable.ic_volume_off);
                        Toast.makeText(this, "Sound on!", Toast.LENGTH_SHORT).show();
                    } else if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                        Toast.makeText(this, "Sound off!", Toast.LENGTH_SHORT).show();
                        item.setIcon(R.drawable.ic_volume_on);
                    }
                }
                return true;



            default:
            return super.onOptionsItemSelected(item);

        }
    }


}
