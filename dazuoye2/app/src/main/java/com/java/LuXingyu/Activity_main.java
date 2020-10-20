package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Activity_main extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private TabLayout tabs;
    private ArrayList<Fragment> fragments;
    private TabLayoutMediator mediator;
    private ImageButton button_wode,button_search,button_fenlei;
    private int fenlei;//0news paper 1news 2paper
    String[] tabs_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager2 = (ViewPager2) findViewById(R.id.viewpager2);
        tabs=(TabLayout) findViewById(R.id.tabs);

        SettingsManager settingsManager=AppManager.getSettingsManager();
        Settings[] s=settingsManager.getSettings();
        if(s[0].getVisible()[0]==true&&s[0].getVisible()[1]==true)
            fenlei=0;
        if(s[0].getVisible()[0]==true&&s[0].getVisible()[1]==false)
            fenlei=1;
        if(s[0].getVisible()[0]==false)
            fenlei=2;


        if(fenlei==0)
            tabs_name = new String[]{"要闻", "论文","数据", "学者","聚类","图谱"};
        else if(fenlei==1)
            tabs_name = new String[]{"要闻","数据", "图谱","聚类","学者"};
        else
            tabs_name = new String[]{"论文","数据", "图谱","聚类","学者"};




        button_wode=(ImageButton)findViewById(R.id.button_wode);
        button_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_main.this, Activity_my.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        button_search=(ImageButton)findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_main.this, Activity_search.class);
                startActivity(intent);
            }
        });

        button_fenlei=(ImageButton)findViewById(R.id.button_fenlei);
        button_fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Activity_main.this, Activity_fenlei.class);
                startActivity(intent);
                finish();
            }
        });

        final Fragment f_yw=Fragment_yw.Instance();
        final Fragment_paper f_paper= Fragment_paper.Instance();
        final Fragment_tp f_tp=Fragment_tp.newInstance("2","1");
        final Fragment_jl f_jl=Fragment_jl.Instance();
        final Fragment_xz f_xz=Fragment_xz.newInstance("2","1");

        //禁用预加载
        viewPager2.setOffscreenPageLimit(6);
        //Adapter
        viewPager2.setAdapter(new FragmentStateAdapter(this) {

            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if(fenlei==0) {
                    if (position == 0)//要闻
                        return f_yw;
                    else if (position == 1)
                        return f_paper;
                    else if (position == 2)//数据
                        return Fragment_sj.newInstance("1", "2");
                    else if (position == 3)//图谱
                        return f_xz;
                    else if (position == 4)//聚类
                        return f_jl;
                    else//学者
                        return f_tp;
                } else if(fenlei==1){
                    if (position == 0)//要闻
                        return f_yw;
                    else if (position == 1)//数据
                        return Fragment_sj.newInstance("1", "2");
                    else if (position == 2)//图谱
                        return f_xz;
                    else if (position == 3)//聚类
                        return f_jl;
                    else//学者
                        return f_tp;
                } else{
                    if (position == 0)
                        return f_paper;
                    else if (position == 1)//数据
                        return Fragment_sj.newInstance("1", "2");
                    else if (position == 2)//图谱
                        return f_xz;
                    else if (position == 3)//聚类
                        return f_jl;
                    else//学者
                        return f_tp;
                }
            }

            @Override
            public int getItemCount() {
                if(fenlei==0)return 6;
                return 5;
            }
        });
        //viewPager 页面切换监听
        viewPager2.registerOnPageChangeCallback(changeCallback);
        mediator = new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //这里可以自定义TabView
                TextView tabView = new TextView(Activity_main.this);
                tabView.setGravity(Gravity.CENTER);
                tabView.setText(tabs_name[position]);
                tab.setCustomView(tabView);
            }
        });
        //要执行这一句才是真正将两者绑定起来
        mediator.attach();
    }
    private ViewPager2.OnPageChangeCallback changeCallback;{
        changeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //可以来设置选中时tab的大小
                int tabCount = tabs.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = tabs.getTabAt(i);
                    assert tab != null;
                    TextView tabView = (TextView) tab.getCustomView();
                    if(i==position){
                        tabView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else{
                        tabView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        mediator.detach();
        viewPager2.unregisterOnPageChangeCallback(changeCallback);
        super.onDestroy();
    }
    @Override
    protected void onRestart(){

        super.onRestart();
    }


}

