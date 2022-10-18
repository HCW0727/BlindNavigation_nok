package com.dds.core.ui.setting;

import static org.webrtc.ContextUtils.getApplicationContext;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.View;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dds.AiDetectActivity;
import com.dds.CallListActivity;
import com.dds.FFmpegRecordActivity;
import com.dds.LauncherActivity;
import com.dds.MainMenuActivity;
import com.dds.core.socket.SocketManager;
import com.dds.core.voip.CallMultiActivity;
import com.dds.core.ui.user.UserListFragment;
import com.dds.core.voip.CallSingleActivity;
import com.dds.core.voip.NavigationTMapActivity;
import com.dds.core.voip.TmapSetting;
import com.dds.webrtc.R;

import java.util.Locale;
import java.util.UUID;


public class SettingFragment extends Fragment {

    String      utteranceId=this.hashCode() + "";
    int         m_iCurTopMenuSel;
    private     TextToSpeech tts;
    private     final boolean           bTTS_Speech   = true;
    private int lang;

    private SettingViewModel notificationsViewModel;
    private Button button,button1,button2,button3,button4,button5;
    private String userId;
    private String avatar;
    private String nickName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(requireActivity()).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        tts = new TextToSpeech( getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                    tts.setSpeechRate(1.4f);
                }
            }
        });

        Log.i("Activity 11------", "main");

        button1 =  root.findViewById(R.id.Btn1);
        button2 =  root.findViewById(R.id.Btn2);
        button3 =  root.findViewById(R.id.Btn3);
        button4 =  root.findViewById(R.id.Btn4);
        button5 =  root.findViewById(R.id.Btn5);


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



        return root;
    }
    //--------------------------------- menu
    //----------------------------------------
    public void Menu1() {
        String st = "AI 장애물 인식기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        //Intent intent = new Intent(MainActivity.this, ActivityTTS.class);
        Intent intent = new Intent(getContext(), AiDetectActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu2()
    {
        String st = "네비게이션 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        //    speech( );
        Intent intent = new Intent(getContext(), NavigationTMapActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu3() {
        String st = "원격접속 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        CallSingleActivity.openActivity(getContext(), "menti", true, getNickName(), false, false);
    }
    //----------------------------------------
    public void Menu4() {
        String st = "블랙박스 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);
        Intent intent = new Intent(getContext(), FFmpegRecordActivity.class);
        startActivity(intent);
    }
    //----------------------------------------
    public void Menu5() {
        String st = "설정 기능을 실행합니다";
        if(bTTS_Speech ==true) tts.speak( st,TextToSpeech.QUEUE_ADD,null, utteranceId);

        Intent intent = new Intent(getContext(), CallListActivity.class);
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

    private void createRoom() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("바로 룸을 만들고 룸에 들어갑니다.");
        builder.setPositiveButton("확인", (dialog, which) -> {
            String room = "testing";
            // 방을 만들고 들어갑니다.
            CallMultiActivity.openActivity(getActivity(),
                    "room-" + room, false);
            //랜덤 방 코드 생성
//            Toast.makeText(getContext(), room, Toast.LENGTH_LONG).show();

        }).setNegativeButton("취소", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public String getNickName() {
        if (TextUtils.isEmpty(nickName)) {
            return userId;
        }
        return nickName;
    }



}