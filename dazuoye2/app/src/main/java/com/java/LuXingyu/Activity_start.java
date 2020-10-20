package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class Activity_start extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        AppManager.getAppManager(this);
        RegDataManager regDataManager=AppManager.getRegDataManager();

        SettingsManager settingsManager=AppManager.getSettingsManager();
        Settings[] s=settingsManager.getSettings();
        if(s.length==0){
            String strings[]={"news","paper"};
            boolean b[]={true,true};
            Settings settings=new Settings(b,strings);

            settingsManager.insertSettings(settings);
            NewsManager newsManager=AppManager.getNewsManager();
            newsManager.getNews("https://covid-dashboard.aminer.cn/api/events/list?type=event&page=1&size=699");

            ScholarManager scholarManager = AppManager.getScholarManager();
            scholarManager.getScholar("https://innovaapi.aminer.cn/predictor/api/v1/valhalla/highlight/get_ncov_expers_list?v=2");

        }

        GetRegdata getRegdata = new GetRegdata(regDataManager);
        if(Connect.networkConnected(this))
            getRegdata.start();

        //60dp转化为px
        float dp = 60;
        final float scale = getResources().getDisplayMetrics().density;
        //由30dp转化来的px
        int px = (int) (dp * scale + 0.5f);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Constant.w = dm.widthPixels;
        Constant.h = dm.heightPixels-px;//得到chart的像素px


        //数据库加载20个新闻


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent();
                intent.setClass(Activity_start.this, Activity_main.class);
                startActivity(intent);
                Activity_start.this.finish();
            }
        }, 1500);

    }
}