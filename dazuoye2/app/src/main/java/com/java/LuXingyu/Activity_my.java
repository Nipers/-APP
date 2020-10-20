package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class Activity_my extends AppCompatActivity {
    private ImageButton button;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        button=(ImageButton)findViewById(R.id.button_shouye);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                overridePendingTransition(0,0);
            }
        });
        relativeLayout=(RelativeLayout) findViewById(R.id.my_star);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_my.this, Activity_mystar.class);
                startActivity(intent);
            }
        });
    }
}