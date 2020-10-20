package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Activity_entry_result extends AppCompatActivity {
    private List<PlagueEntity> newsMyList = new ArrayList<>();
    private EntryAdapter newsAdapter;
    private String query;
    private RecyclerView recyclerView;
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_result);
        Intent intent=getIntent();
        query=intent.getStringExtra("query");


        PlagueEntity[] p=EntityManager.getSearchedEntity(query);
        for(int i=0;i<p.length;i++)
            newsMyList.add(p[i]);


        recyclerView=(RecyclerView)findViewById(R.id.recycler_entry);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initNews();
        newsAdapter=new EntryAdapter(newsMyList,Activity_entry_result.this);
        recyclerView.setAdapter(newsAdapter);
        
//        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Intent intent=new Intent();
//
//                String id=(newsMyList.get(position)).getID();
//
//                ReadID readID=new ReadID(id);
//                ReadIDManager readIDManager=AppManager.getReadIDManager();
//                readIDManager.insertReadID(readID);
//
//                intent.putExtra("id",id);
//                intent.setClass(Activity_Search_result.this, Activity_news.class);
//                startActivity(intent);
//            }
//        });
//        button=(ImageButton)findViewById(R.id.button_search_back);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
    private void initNews(){
//        NewsManager newsManager=AppManager.getNewsManager();
//        News n[]=newsManager.getSearchedNews(query);
//        for(int i=0;i<n.length;i++)
//            newsMyList.add(new Introduction(n[i]));

    }
}