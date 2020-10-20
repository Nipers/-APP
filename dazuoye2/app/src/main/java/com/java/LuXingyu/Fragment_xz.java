package com.java.LuXingyu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Fragment_xz extends Fragment {

    private List<ScholarIntroduction> newsMyList = new ArrayList<>();
    private ScholarAdapter scholarAdapter;
    private View rootView;
    private RecyclerView recyclerView;
    Button button1,button2;
    int xz=1;

    public Fragment_xz() {
        // Required empty public constructor
        xz=1;
    }


    public static Fragment_xz newInstance(String param1, String param2) {
        Fragment_xz fragment = new Fragment_xz();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_xz, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recycler_xz);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        scholarAdapter=new ScholarAdapter(newsMyList,getContext());
        recyclerView.setAdapter(scholarAdapter);
        initScholar();

        button1=(Button)rootView.findViewById(R.id.button_xz_1);
        button2=(Button)rootView.findViewById(R.id.button_xz_2);
        button1.setBackgroundColor(0xFFD3D3D3);
        button2.setBackgroundColor(Color.WHITE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xz=1;
                initScholar();
                button1.setBackgroundColor(0xFFD3D3D3);
                button2.setBackgroundColor(Color.WHITE);
                scholarAdapter.notifyDataSetChanged();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xz=2;
                initScholar();
                button2.setBackgroundColor(0xFFD3D3D3);
                button1.setBackgroundColor(Color.WHITE);
                scholarAdapter.notifyDataSetChanged();
            }
        });


        scholarAdapter.notifyDataSetChanged();

        scholarAdapter.setOnItemClickListener(new com.java.LuXingyu.ScholarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent();

                String id=(newsMyList.get(position)).getId();

                intent.putExtra("id",id);
                intent.setClass(getActivity(), Activity_xz.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
    void initScholar(){
        newsMyList.clear();
        if(Connect.networkConnected(getActivity())) {
            ScholarManager scholarManager = AppManager.getScholarManager();
            if(xz==1){
                ArrayList<ScholarIntroduction> temp = scholarManager.getOneTypeScholar(false);
                for(int i=0;i<temp.size();i++)
                    newsMyList.add(temp.get(i));
            }

            if(xz==2){
                ArrayList<ScholarIntroduction> temp = scholarManager.getOneTypeScholar(true);
                for(int i=0;i<temp.size();i++)
                    newsMyList.add(temp.get(i));
            }
        }
    }

}