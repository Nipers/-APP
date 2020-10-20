package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

public class Activity_search extends AppCompatActivity {
    private TextView t1,t2,t3,t4,t5;
    private String s1,s2,s3,s4,s5;
    private ImageButton button;
    private Button button_begin_search;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        t1=(TextView)findViewById(R.id.search_1);
        t2=(TextView)findViewById(R.id.search_2);
        t3=(TextView)findViewById(R.id.search_3);
        t4=(TextView)findViewById(R.id.search_4);
        t5=(TextView)findViewById(R.id.search_5);

        HistoryManager historyManager=AppManager.getHistoryManager();
        History history[]=historyManager.getAllHistory();
        if(history.length>0)t1.setText(history[history.length-1].content);
        if(history.length>1)t2.setText(history[history.length-2].content);
        if(history.length>2)t3.setText(history[history.length-3].content);
        if(history.length>3)t4.setText(history[history.length-4].content);
        if(history.length>4)t5.setText(history[history.length-5].content);

        button=(ImageButton)findViewById(R.id.button_search_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView=(SearchView)findViewById(R.id.search_view);
        searchView.setSubmitButtonEnabled(true);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                HistoryManager historyManager=AppManager.getHistoryManager();
                History history[]=historyManager.getAllHistory();
                if(history.length>0)t1.setText(history[history.length-1].content);
                if(history.length>1)t2.setText(history[history.length-2].content);
                if(history.length>2)t3.setText(history[history.length-3].content);
                if(history.length>3)t4.setText(history[history.length-4].content);
                if(history.length>4)t5.setText(history[history.length-5].content);

                if (TextUtils.isEmpty(newText))
                    return false;
                //do something
                return false;

            }
            public boolean onQueryTextSubmit(final String query) {
                HistoryManager historyManager=AppManager.getHistoryManager();
                History h=new History(query);
                History history[]=historyManager.getAllHistory();
                historyManager.insertReadID(h);
                if(history.length>0)t1.setText(history[history.length-1].content);
                if(history.length>1)t2.setText(history[history.length-2].content);
                if(history.length>2)t3.setText(history[history.length-3].content);
                if(history.length>3)t4.setText(history[history.length-4].content);
                if(history.length>4)t5.setText(history[history.length-5].content);

                Intent intent =new Intent();
                intent.putExtra("query",query);
                intent.putExtra("search0_jl1",0);
                intent.setClass(Activity_search.this, Activity_Search_result.class);
                startActivity(intent);
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        HistoryManager historyManager=AppManager.getHistoryManager();
        History history[]=historyManager.getAllHistory();
        if(history.length>0)t1.setText(history[history.length-1].content);
        if(history.length>1)t2.setText(history[history.length-2].content);
        if(history.length>2)t3.setText(history[history.length-3].content);
        if(history.length>3)t4.setText(history[history.length-4].content);
        if(history.length>4)t5.setText(history[history.length-5].content);
        super.onStart();
    }

}