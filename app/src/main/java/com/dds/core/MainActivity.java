package com.dds.core;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dds.App;
import com.dds.LauncherActivity;
import com.dds.core.base.BaseActivity;
import com.dds.core.socket.IUserState;
import com.dds.core.socket.SocketManager;
import com.dds.core.voip.Consts;
import com.dds.core.voip.VoipReceiver;
import com.dds.webrtc.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * main
 */
public class MainActivity extends BaseActivity implements IUserState {
    private static final String TAG = "MainActivity";
    boolean isFromCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_user,
                R.id.navigation_setting)
                .build();
        // Action Bar 팔로우 연동 설정
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // Nav 팔로우 연동 설정
        NavigationUI.setupWithNavController(navView, navController);
        // 로그인 상태 콜백 설정
        SocketManager.getInstance().addUserStateCallback(this);
        isFromCall = getIntent().getBooleanExtra("isFromCall", false);
        Log.d(TAG, "onCreate isFromCall = " + isFromCall);
        if (isFromCall) { //권한이 없습니다. 발신자 요청 권한이 여기에 있습니다.
            initCall();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart isFromCall = " + isFromCall);
    }

    @Override
    public void userLogin() {

    }

    private void initCall() {
        //프런트입니다, 방송 발신 권한 상승 판단 팝업
        Intent viop = new Intent();
        Intent intent = getIntent();
        viop.putExtra("room", intent.getStringExtra("room"));
        viop.putExtra("audioOnly", intent.getBooleanExtra("audioOnly", false));
        viop.putExtra("inviteId", intent.getStringExtra("inviteId"));
        viop.putExtra("inviteUserName", intent.getStringExtra("inviteUserName"));
//        viop.putExtra("msgId", intent.getLongExtra("msgId", 0));
        viop.putExtra("userList", intent.getStringExtra("userList"));
        viop.setAction(Consts.ACTION_VOIP_RECEIVER);
        viop.setComponent(new ComponentName(App.getInstance().getPackageName(), VoipReceiver.class.getName()));
        sendBroadcast(viop);
    }

    @Override
    public void userLogout() {
        if (!this.isFinishing()) {
            Intent intent = new Intent(this, LauncherActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            super.onBackPressed();
        }

    }
}
