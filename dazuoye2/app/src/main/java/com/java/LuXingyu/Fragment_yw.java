package com.java.LuXingyu;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_yw extends Fragment implements OnTouchUpListener{

    private static Fragment_yw f_yw;
    private PullLoadRecyclerViewLayout mLayout;
    private List<Introduction> newsMyList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private View rootView;
    private View mHeaderView;
    private View mFooterView;

    public int load_time;

    public Fragment_yw() {
        // Required empty public constructor
    }

    public static Fragment_yw Instance() {
        if(f_yw==null) {
            f_yw= new Fragment_yw();
        }
        return f_yw;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNews();
        load_time=0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        newsAdapter=new NewsAdapter(newsMyList);
        rootView=inflater.inflate(R.layout.fragment_yw, container, false);
        LayoutInflater my_inflater = LayoutInflater.from(getActivity());
        mHeaderView = my_inflater.inflate(R.layout.co_refresh_header, null);
        mFooterView = my_inflater.inflate(R.layout.co_refresh_footer, null);
        mLayout = (PullLoadRecyclerViewLayout)rootView.findViewById(R.id.pull_load_layout);
        mLayout.addFooterView(mFooterView, DisplayUtil.dpToPx(getActivity(), 40));
        mLayout.addHeaderView(mHeaderView, DisplayUtil.dpToPx(getActivity(), 60));
        mLayout.setMyRecyclerView(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false),
                newsAdapter, true);
        mLayout.addOnTouchUpListener(this);

        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();
                intent.putExtra("news0_star1",0);//news or star
                String id=(newsMyList.get(position)).getID();
//                ReadID readID=new ReadID(id);
//                ReadIDManager readIDManager=AppManager.getReadIDManager();
//                readIDManager.insertReadID(readID);

                intent.putExtra("id",id);
                intent.setClass(getActivity(), Activity_news.class);
                startActivity(intent);
            }
        });
        return rootView;
    }


    private void initNews(){
        newsMyList.clear();
        if(newsMyList.isEmpty()) {
            if(Connect.networkConnected(getActivity())) {
                NewsManager newsManager = AppManager.getNewsManager();
                Introduction n[] = newsManager.getNews("https://covid-dashboard.aminer.cn/api/events/list?type=news&page=1&size=15");
                for (int i = 0; i < 15; i++)
                    newsMyList.add(n[i]);
            }
        }
    }

    private void onDataRefreshing() {
        if(Connect.networkConnected(getActivity())) {
            newsMyList.clear();
            NewsManager newsManager = AppManager.getNewsManager();
            Introduction n[] = newsManager.getNews("https://covid-dashboard.aminer.cn/api/events/list?type=news&page=1&size=15");
            for (int i = 0; i < 15; i++)
                newsMyList.add(n[i]);
            //更新adapter
            newsAdapter.notifyDataSetChanged();
            //停止更新动作

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefreshFinish(true);
            }
        }, 500);
    }

    private void onDataLoadingMore() {
        if(Connect.networkConnected(getActivity())) {
            //增加新闻
            NewsManager newsManager = AppManager.getNewsManager();
            Introduction n[] = newsManager.getNews("https://covid-dashboard.aminer.cn/api/events/list?type=news&page=" + Integer.toString(load_time + 2) + "&size=15");
            load_time++;
            for (int i = 0; i < 15; i++)
                newsMyList.add(n[i]);
            //更新adapter
            newsAdapter.notifyDataSetChanged();
            //更新之后滑到最底端
            //mLayout.setRecyclerViewScrollToPosition(newsAdapter.getItemCount()-1);

        }
        //停止更新动作
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadMoreFinish(true);
            }
        }, 300);
    }

    @Override
    public void OnRefreshing() {
        mLayout.setHeaderState(PullLoadRecyclerViewLayout.HEADER_STATE_REFRESHING);
        onDataRefreshing();
    }

    @Override
    public void OnLoading() {
        mLayout.setFooterState(PullLoadRecyclerViewLayout.FOOTER_STATE_LOADING);
        onDataLoadingMore();
    }

    private void onRefreshFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setHeaderState(success ? PullLoadRecyclerViewLayout.HEADER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.HEADER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }

    private void onLoadMoreFinish(final boolean success) {
        new Handler() {
            @Override
            public void handleMessage(Message message) {
                mLayout.setFooterState(success ? PullLoadRecyclerViewLayout.FOOTER_STATE_COMPLETE :
                        PullLoadRecyclerViewLayout.FOOTER_STATE_FAIL);
            }
        }.sendEmptyMessageDelayed(0, 100);
    }


    @Override
    public void onStart() {
        newsAdapter.notifyDataSetChanged();
        super.onStart();
    }

}