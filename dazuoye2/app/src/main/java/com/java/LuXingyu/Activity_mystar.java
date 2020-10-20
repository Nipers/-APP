package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Activity_mystar extends AppCompatActivity {
    private ImageButton button;
    private RecyclerView recyclerView;
    private CardView item_news;
    private NewsAdapter newsAdapter;
    private List<Introduction> starMyList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystar);
        button=(ImageButton)findViewById(R.id.button_star_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recycler_mystar);
        initStar();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        newsAdapter=new NewsAdapter(starMyList);
        recyclerView.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                String id=(starMyList.get(position)).getID();
                intent.putExtra("id",id);
                intent.setClass(Activity_mystar.this, Activity_news.class);
                startActivity(intent);
            }
        });
    }
    private void initStar(){
        //增加收藏的新闻
        NewsManager newsManager=AppManager.getNewsManager();
        ReadIDManager readIDManager=AppManager.getReadIDManager();
        ReadID[] readID=readIDManager.getAllReadID();
        for(int i=readID.length-1;i>=0;i--){
            starMyList.add(new Introduction(newsManager.getContent(readID[i].ID)[0]));
        }
    }

}