package com.java.LuXingyu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class Fragment_tp extends Fragment {

    View rootView;
    SearchView searchView;

    public Fragment_tp() {
        // Required empty public constructor
    }

    public static Fragment_tp newInstance(String param1, String param2) {
        Fragment_tp fragment = new Fragment_tp();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_tp, container, false);
        searchView=(SearchView)rootView.findViewById(R.id.entry_search);
        searchView.setSubmitButtonEnabled(true);
        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText))
                    return false;
                //do something
                return false;

            }
            public boolean onQueryTextSubmit(final String query) {
                Intent intent =new Intent();
                intent.putExtra("query",query);
                intent.setClass(getActivity(), Activity_entry_result.class);
                startActivity(intent);
                return false;
            }
        });
        return rootView;
    }

}