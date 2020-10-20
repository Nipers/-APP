package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Activity_Search_result extends AppCompatActivity {
    private List<Introduction> newsMyList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private String query;
    private RecyclerView recyclerView;
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent=getIntent();
        int search_or_jl=intent.getIntExtra("search0_jl1",0);
        if(search_or_jl==0){
            query=intent.getStringExtra("query");
            NewsManager newsManager=AppManager.getNewsManager();
            News n[]=newsManager.getSearchedNews(query);
            for(int i=0;i<n.length;i++)
                newsMyList.add(new Introduction(n[i]));
            newsAdapter=new NewsAdapter(newsMyList);
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent=new Intent();
                    String id=(newsMyList.get(position)).getID();

                    ReadID readID=new ReadID(id);
                    ReadIDManager readIDManager=AppManager.getReadIDManager();
                    readIDManager.insertReadID(readID);

                    intent.putExtra("id",id);
                    intent.setClass(Activity_Search_result.this, Activity_news.class);
                    startActivity(intent);
                }
            });
        }

        //initNews();

        button=(ImageButton)findViewById(R.id.button_search_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initNews(){
        NewsManager newsManager=AppManager.getNewsManager();
        News n[]=newsManager.getSearchedNews(query);
        for(int i=0;i<n.length;i++)
            newsMyList.add(new Introduction(n[i]));

    }
}