package com.example.keeprawteach.legacydigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.keeprawteach.legacydigital.Main.MKUMain;

public class StartApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);

//        new Handler().postDelayed(new Runnable(){
//            @Override
//            public void run() {
//                /* Create an Intent that will start the Menu-Activity. */
//
//                Intent intent=new Intent(StartApp.this, MKUMain.class);
//
//                startActivity(intent);
//
//                finish();
//            }
//        }, 2000);
    }
}
