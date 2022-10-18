package com.dds;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.dds.webrtc.R;

import java.util.Locale;

public class AiDetectActivity extends AppCompatActivity {
    private     final boolean           bTTS_Speech   = true;
    String      utteranceId=this.hashCode() + "";
    private     TextToSpeech ttsObj;
    VideoView   videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_detect);

        videoView = findViewById(R.id.vv_playback);
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

        ttsObj = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    ttsObj.setLanguage(Locale.KOREAN);
                    ttsObj.setPitch(1.f);
                    ttsObj.setSpeechRate(1.4f);
                    ttsObj.getVoice();
                    ttsObj.getVoices();

                }
            }
        });
        Button button1 =  findViewById(R.id.btn1);
        Button button2 =  findViewById(R.id.btn2);
        Button button3 =  findViewById(R.id.btn3);
        Button button4 =  findViewById(R.id.btn4);

        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu1();
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu2();
            }
        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu3();
            }
        });

        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu4();
            }
        });


        Menu2( );
    }
    //-----------------------------
    public void   Menu1( ) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        //    intent.setComponent(new ComponentName("com.example.stt_demo2","com.example.stt_demo2.MainActivity"));
        intent.setComponent(new ComponentName("org.tensorflow.lite.examples.detection",
                "org.tensorflow.lite.examples.detection.DetectorActivity"));
        startActivity(intent);
    }
    //-----------------------------
    public void   Menu2( ) {
        videoView.stopPlayback();
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cwhuh));
        videoView.start();
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.hide();
                //videoView.pause();
            }
        }, 10);
    }
    //-----------------------------
    public void   Menu3( ) {
        videoView.stopPlayback();
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cwhuh));
        videoView.start();
        videoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.hide();
                //videoView.pause();
            }
        }, 10);
    }
    //-----------------------------
    public void   Menu4( ) {
        ttsObj.stop();
        String  st;
        st = "AI 장애물 인식기능을 종료합니다";
        if(bTTS_Speech ==true) ttsObj.speak( st, TextToSpeech.QUEUE_ADD,null, utteranceId);
        finish( );
        return;
    }
    //-----------------------------

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if( videoView.isPlaying() ) videoView.pause();;
        String   st = "onKeyDown-----" + String.valueOf( keyCode );
        Log.i("--", st);
        if( keyCode == 25 )  Menu1( );
        if( keyCode == 24 )  Menu2( );
        if( keyCode == 96 )  Menu3( );
        if( keyCode == 97 )  Menu4( );
        if( keyCode ==  4 )  Menu4( );
        return true;
    }

}