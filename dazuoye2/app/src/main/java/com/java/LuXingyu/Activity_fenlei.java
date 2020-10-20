package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Activity_fenlei extends AppCompatActivity {
    private LinearLayout yixuan,daixuan;
    private Button button_news,button_paper,button_news_2,button_paper_2,button_bianji;
    private ImageButton button_fenlei_back;
    private int num_yixuan,bianji;
    private int news,paper;
    TranslateAnimation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        yixuan=(LinearLayout)this.findViewById(R.id.yixuan);
        daixuan=(LinearLayout)this.findViewById(R.id.daixuan);
        button_paper=new Button(this);
        button_news=new Button(this);
        button_paper_2=new Button(this);
        button_news_2=new Button(this);
        button_news.setText("新闻");
        button_paper.setText("论文");
        button_news_2.setText("新闻");
        button_paper_2.setText("论文");
        SettingsManager settingsManager=AppManager.getSettingsManager();
        Settings[] s=settingsManager.getSettings();
        num_yixuan=0;
        news=1;
        paper=1;
        if(s[0].visible[0]) {
            yixuan.addView(button_news);
            num_yixuan++;
            news=0;
        }
        else
            daixuan.addView(button_news_2);
        if(s[0].visible[1]) {
            yixuan.addView(button_paper);
            num_yixuan++;
            paper=0;
        }
        else
            daixuan.addView(button_paper_2);

        bianji=1;

        button_news.setEnabled(false);
        button_news_2.setEnabled(false);
        button_paper.setEnabled(false);
        button_paper_2.setEnabled(false);

        anim = new TranslateAnimation(button_news.getWidth(),
                button_news.getWidth() + 5, button_news.getHeight(), button_news.getHeight());
        anim.setInterpolator(new CycleInterpolator(3f));  //循环次数
        //CycleInterpolator：动画从开始到结束，变化率是循环给定次数的正弦曲线。
        anim.setDuration( 400 );

        button_bianji=(Button)findViewById(R.id.button_bianji);
        button_fenlei_back=(ImageButton)findViewById(R.id.button_fenlei_back);
        button_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bianji==1) {
                    button_news.startAnimation(anim);
                    button_paper.startAnimation(anim);
                    button_news_2.startAnimation(anim);
                    button_paper_2.startAnimation(anim);
                    button_news.setEnabled(true);
                    button_news_2.setEnabled(true);
                    button_paper.setEnabled(true);
                    button_paper_2.setEnabled(true);
                    bianji=0;
                    button_bianji.setText("完成");
                    button_fenlei_back.setEnabled(false);
                }else{
                    if(num_yixuan>0) {
                        button_news.setEnabled(false);
                        button_news_2.setEnabled(false);
                        button_paper.setEnabled(false);
                        button_paper_2.setEnabled(false);
                        button_fenlei_back.setEnabled(true);
                        button_bianji.setText("编辑");
                        bianji = 1;
                        Toast toast=Toast.makeText(Activity_fenlei.this,"编辑完成",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else{
                        Toast toast=Toast.makeText(Activity_fenlei.this,"请至少选择一项",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });


        button_fenlei_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("news",news);
                intent.putExtra("paper",paper);
                intent.setClass(Activity_fenlei.this, Activity_main.class);
                startActivity(intent);
                finish();
            }
        });

        button_paper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                paper=1;
                yixuan.removeView(button_paper);
                daixuan.addView(button_paper_2);
                button_paper.startAnimation(anim);
                num_yixuan--;
                SettingsManager settingsManager=AppManager.getSettingsManager();
                Settings[] s=settingsManager.getSettings();
                s[0].visible[1]=false;
                settingsManager.update(s[0]);
            }
        });

        button_news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                news=1;
                yixuan.removeView(button_news);
                daixuan.addView(button_news_2);
                button_news.startAnimation(anim);
                num_yixuan--;
                SettingsManager settingsManager=AppManager.getSettingsManager();
                Settings[] s=settingsManager.getSettings();
                s[0].visible[0]=false;
                settingsManager.update(s[0]);
            }
        });

        button_paper_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                paper=0;
                yixuan.addView(button_paper);
                daixuan.removeView(button_paper_2);
                button_paper_2.startAnimation(anim);
                num_yixuan++;
                SettingsManager settingsManager=AppManager.getSettingsManager();
                Settings[] s=settingsManager.getSettings();
                s[0].visible[1]=true;
                settingsManager.update(s[0]);
            }
        });

        button_news_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                news=0;
                yixuan.addView(button_news);
                daixuan.removeView(button_news_2);
                button_news_2.startAnimation(anim);
                num_yixuan++;
                SettingsManager settingsManager=AppManager.getSettingsManager();
                Settings[] s=settingsManager.getSettings();
                s[0].visible[0]=true;
                settingsManager.update(s[0]);
            }
        });
    }


}

