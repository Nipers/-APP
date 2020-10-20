package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Activity_xz extends AppCompatActivity {
    private TextView name,aff,aff_zh,position,bio,edu,work;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xz);



        name=(TextView)findViewById(R.id.textView_xz_name);
        aff=(TextView)findViewById(R.id.text_xz_school);
        aff_zh=(TextView)findViewById(R.id.text_xz_school_zh);
        position=(TextView)findViewById(R.id.text_xz_position);
        bio=(TextView)findViewById(R.id.xz_bio);
        edu=(TextView)findViewById(R.id.xz_edu);
        work=(TextView)findViewById(R.id.xz_work);
        view=(LinearLayout)findViewById(R.id.lin_avt);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");

        ScholarManager Manager=AppManager.getScholarManager();
        Scholar[] s=Manager.getScholarByID(id);


        if(s[0].getName_zh().length()<2)
            name.setText(s[0].getName());
        else name.setText(s[0].getName_zh());

        aff.setText("职位："+s[0].getProfile().getAffiliation());
        aff_zh.setText(s[0].getProfile().getAffiliation_zh());
        position.setText("单位："+s[0].getProfile().getPosition());
        bio.setText("个人简介：\n"+s[0].getProfile().getBio()+"\n");
        edu.setText("教育背景：\n"+s[0].getProfile().getEdu()+"\n");
        work.setText("工作经历：\n"+s[0].getProfile().getWork()+"\n");

        ImageView imageView = new ImageView(this);
        Glide.with(this).load(s[0].getAvatar()).into(imageView);
        view.addView(imageView);


    }
}