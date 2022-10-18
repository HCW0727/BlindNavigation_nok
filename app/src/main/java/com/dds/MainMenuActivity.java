package com.dds;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dds.core.voip.NavigationTMapActivity;
import com.dds.webrtc.R;

import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {
    String      utteranceId=this.hashCode() + "";
    int         m_iCurTopMenuSel;
    private     TextToSpeech tts;
    private     final boolean           bTTS_Speech   = true;
    private int lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);



        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                    tts.setSpeechRate(1.4f);
                }
            }
        });

        Log.i("Activity 11------", "main");

        Button button1 =  findViewById(R.id.Btn1);
        Button button2 =  findViewById(R.id.Btn2);
        Button button3 =  findViewById(R.id.Btn3);
        Button button4 =  findViewById(R.id.Btn4);
        Button button5 =  findViewById(R.id.Btn5);


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

        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu5();
            }
        });
    }
    //--------------------------------- menu
    //----------------------------------------
    public void Menu1() {
        String st = "AI 장애물 인식기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        //Intent intent = new Intent(MainActivity.this, ActivityTTS.class);
        Intent intent = new Intent(MainMenuActivity.this, AiDetectActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu2()
    {
        String st = "네비게이션 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
    //    speech( );
        Intent intent = new Intent(MainMenuActivity.this, NavigationTMapActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu3() {
        String st = "원격접속 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        //intent.setComponent(new ComponentName("com.dds.webrtc.debug","com.dds.LauncherActivity"));
        //startActivity(intent);
        Intent intent = new Intent(MainMenuActivity.this, LauncherActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu4() {
        String st = "블랙박스 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        Intent intent = new Intent(MainMenuActivity.this, FFmpegRecordActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu5() {
        String st = "긴급 전화를 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);

        Intent intent = new Intent(MainMenuActivity.this, CallListActivity.class);
        startActivity(intent);

        /*
        String tel_number = "tel:01076373768";
        PermissionManager mPermissionManager = new PermissionManager();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){

            return;
        }
       Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(tel_number));
        startActivity(intent);
       */
     }
    //-------------------------------------------------
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("video/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        Uri uri = Uri.parse( Environment.getExternalStorageDirectory().getPath()+ "/Download/");
        i.setDataAndType(uri, "application/*");

        // pass the constant to compare it
        // with the returned requestCode
        int SELECT_PICTURE = 200;
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    //-------------------------------------------------
    public int   RunCurTopMenu( int iCurTopMenuSel ) {
        String   st = "";
        if( iCurTopMenuSel == 25 ) {
            m_iCurTopMenuSel = 0;
            Menu1();
        }
        if( iCurTopMenuSel == 24 ) {
            m_iCurTopMenuSel = 0;
            Menu2();
        }
        if( iCurTopMenuSel == 96 ) {
            m_iCurTopMenuSel = 0;
            Menu3();
        }
        if( iCurTopMenuSel == 97 ) {
            m_iCurTopMenuSel = 0;
            Menu4();
        }
        Log.i("-------------------", st);
        return 1;
    }
    //----------------------------------------------------
    public int   PrintCurTopMenu( int iCurTopMenuSel ) {
        String   st = "";
        if( iCurTopMenuSel == 25 ) {
            if( m_iCurTopMenuSel == 25 ) {
                RunCurTopMenu(iCurTopMenuSel);
                return 1;
            }
            else {
                st = "AI 장애물 인식기능";
                m_iCurTopMenuSel = iCurTopMenuSel;
            }
        }
        if( iCurTopMenuSel == 24 ) {
            if( m_iCurTopMenuSel == 24 ) {
                RunCurTopMenu(iCurTopMenuSel);
                return 1;
            }
            else {
                st = "네비게이션 기능";
                m_iCurTopMenuSel = iCurTopMenuSel;
            }
        }
        if( iCurTopMenuSel == 96 ) {
            if( m_iCurTopMenuSel == 96 ) {
                RunCurTopMenu(iCurTopMenuSel);
                return 1;
            }
            else {
                st = "원격접속 기능";
                m_iCurTopMenuSel = iCurTopMenuSel;
            }
        }
        if( iCurTopMenuSel == 97 ) {
            if( m_iCurTopMenuSel == 97 ) {
                RunCurTopMenu(iCurTopMenuSel);
                return 1;
            }
            else {
                st = "블랙박스 기능";
                m_iCurTopMenuSel = iCurTopMenuSel;
            }
        }
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        Log.i("-------------------", st);
        return 1;
    }
    //--------------------------------------------
    public  int   GetCurTopMenu( int iSel ) {
        return 1;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String   st = "onKeyDown" + String.valueOf( keyCode );
        Log.i("-------------------", st);
        PrintCurTopMenu( keyCode );
        return true;
    }
    //----------------------------------------

}