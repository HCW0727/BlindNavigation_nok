package com.dds;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.dds.webrtc.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CallListActivity extends AppCompatActivity {
    private File file;
    private List myList;


    //1. String 배열 생성
    final String[] book = {"Algorithm", "Computer structure",
            "Computer Network", "DataBase", "Java Programming", "Data Science"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_list);

        Log.i("h","start");


        myList = new ArrayList();

        String rootSD = Environment.getExternalStorageDirectory().toString();
        file = new File( rootSD + "/Download" ) ;
        File list[] = file.listFiles();

        for( int i=0; i<list.length; i++)
        {
            myList.add( list[i].getName() );
            Log.i("h",list[i].getName() );
        }



        setTitle("ListView Example");
        //2. 리스트뷰 변수 생성 및 위젯 연결
        ListView mylistview = (ListView)findViewById(R.id.listview1);





        //3. ArrayAdapter 생성 , 항목을 simplelist로 나열 설정 및 String 배열 연결
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, book);
        //4. 어댑터를 리스트뷰에 연결
        mylistview.setAdapter(adapter);

        //5. 리스너 설정
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CallListActivity.this, book[i] + "을 선택하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //--------------------------------- menu
    //----------------------------------------
    public void Menu1() {

    }
    //----------------------------------------
    public void Menu2()
    {

    }
    //----------------------------------------
    public void Menu3() {

    }
    //----------------------------------------
    public void Menu4() {
        finish( );
        return;
    }
    //----------------------------------------

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String   st = "onKeyDown" + String.valueOf( keyCode );

        Log.i("-------------------", st);
        if( keyCode == 25 ) Menu1( );
        if( keyCode == 24 ) Menu2( );
        if( keyCode == 96 ) Menu3( );
        if( keyCode == 97 ) Menu4( );
        if( keyCode ==  4 ) Menu4( );
        return true;
    }

}