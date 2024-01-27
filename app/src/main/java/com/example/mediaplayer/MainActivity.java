package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton playButton,pauseButton;
    private MediaPlayer mediaPlayer;
    private TextView durationTextView;

    int finalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton_Id);
        pauseButton = findViewById(R.id.pauseButton_Id);
        durationTextView = findViewById(R.id.durationTextView_Id);

        mediaPlayer = MediaPlayer.create(this,R.raw.kalarab_gojol);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.playButton_Id){
            if (mediaPlayer!=null){

                mediaPlayer.start();
                showDetails();
                Toast.makeText(MainActivity.this,"Play",Toast.LENGTH_SHORT).show();
            }

        }
        if (v.getId()==R.id.pauseButton_Id){
            if (mediaPlayer!=null){

                mediaPlayer.pause();
                Toast.makeText(MainActivity.this,"Paused",Toast.LENGTH_SHORT).show();
            }

        }


    }

    public void showDetails(){


        finalTime = mediaPlayer.getDuration();
        durationTextView.setText(String.format("%d:%d",

                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)

                        finalTime)))
        );

    }

    protected void onDestroy(){

        if (mediaPlayer!=null && mediaPlayer.isPlaying()){

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(MainActivity.this,"Paused",Toast.LENGTH_SHORT).show();
        }

        super.onDestroy();

    }


}