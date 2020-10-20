package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_news extends AppCompatActivity {
    private ImageButton button;
    private LinearLayout L_star,L_share;
    private TextView text_title,text_from,text_time,text_article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        text_title=(TextView)findViewById(R.id.item_text_title);
        text_from=(TextView)findViewById(R.id.item_text_from);
        text_time=(TextView)findViewById(R.id.item_text_date);
        text_article=(TextView)findViewById(R.id.text_article);
        Intent intent=getIntent();

        String id=intent.getStringExtra("id");


        ReadID readID=new ReadID(id);
        ReadIDManager readIDManager=AppManager.getReadIDManager();
        readIDManager.insertReadID(readID);

        NewsManager newsManager=AppManager.getNewsManager();
        News[] n=newsManager.getContent(id);
        text_article.setText(n[0].getContent());

        text_article.setText(newsManager.getContent(id)[0].getContent());
        text_title.setText(newsManager.getContent(id)[0].getTitle());
        text_from.setText(newsManager.getContent(id)[0].getSource());
        text_time.setText(newsManager.getContent(id)[0].getDate());

//        n[0].setRead(true);
//        newsManager.updateIsRead(n[0]);


        button=(ImageButton)findViewById(R.id.button_news_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        L_star=(LinearLayout)findViewById(R.id.star);
        L_share=(LinearLayout)findViewById(R.id.share);
        L_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_news.this, "已收藏", Toast.LENGTH_SHORT).show();
                //
            }
        });
        L_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                //startActivity(Intent.createChooser(shareIntent, "Share to..."));
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "f分享");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "分享一条新闻："+text_title.getText()+"\n请使用战疫前线app打开此新闻");
                shareIntent = Intent.createChooser(shareIntent, "分享");
                startActivity(shareIntent);
//                PopupMenu popup = new PopupMenu(Activity_news.this, L_share);
//                popup.getMenu().add(0,1,0,"微信").setIcon(R.mipmap.icon_wechat);
//                popup.getMenu().add(0,2,0,"微博").setIcon(R.mipmap.icon_sina);
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if(item.getItemId()==1)
//                            Toast.makeText(Activity_news.this, "wechat", Toast.LENGTH_SHORT).show();//test
//                        if(item.getItemId()==2)
//                            Toast.makeText(Activity_news.this, "weibo", Toast.LENGTH_SHORT).show();//test
//                        return true;
//                    }
//                });
//                popup.show(); //showing popup menu
            }
        });
    }
}