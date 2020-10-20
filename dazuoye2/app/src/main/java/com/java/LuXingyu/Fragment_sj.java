package com.java.LuXingyu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_sj#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_sj extends Fragment {

    private Button button_province;
    private RadioGroup mRadioGroup;
    int qz_zy_sw_ys=0;
    String[] Province = {"香港","新疆","北京","四川","甘肃","上海","广东","台湾","河北","陕西","山西","云南","重庆"
            ,"内蒙古","山东","浙江","天津","辽宁","福建","江苏","海南","澳门","吉林","湖北","江西","黑龙江","安徽"
            ,"贵州","湖南","河南","广西","宁夏","青海","西藏"};
    String[] Country = {"阿根廷","澳大利亚","巴西","加拿大","中国","埃及","德国","希腊","印度","意大利",
            "日本","墨西哥","蒙古","秘鲁","俄罗斯","英国","美国"};

    private Button button_country;
    public Fragment_sj() {
        // Required empty public constructor
        qz_zy_sw_ys=0;
    }

    public static Fragment_sj newInstance(String param1, String param2) {
        Fragment_sj fragment = new Fragment_sj();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_sj, container, false);
        // Inflate the layout for this fragment

        //RegDataManager.GetRegdata getRegdata
        mRadioGroup=(RadioGroup)rootView.findViewById(R.id.group);
        button_province=(Button)rootView.findViewById(R.id.button_province);
        button_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), button_province);
                for(int i=0;i<Province.length;i++){
                    popup.getMenu().add(1,i,0,Province[i]);
                }

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "点击了"+Province[item.getItemId()], Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.putExtra("province0_country1",0);
                        intent.putExtra("qz",qz_zy_sw_ys);
                        intent.putExtra("item",item.getItemId());
                        intent.setClass(getActivity(), Activity_chart.class);
                        startActivity(intent);
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });

        button_country=(Button)rootView.findViewById(R.id.button_country);
        button_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), button_country);
                for(int i=0;i<Country.length;i++){
                    popup.getMenu().add(1,i,0,Country[i]);
                }

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(getActivity(), "点击了"+Country[item.getItemId()], Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        intent.putExtra("province0_country1",1);
                        intent.putExtra("qz",qz_zy_sw_ys);
                        intent.putExtra("item",item.getItemId());
                        intent.setClass(getActivity(), Activity_chart.class);
                        startActivity(intent);
                        return true;
                    }
                });
                popup.show(); //showing popup menu
            }
        });
        //mRadioGroup.setOnCheckedChangeListener(new CheckListener());
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId) {
                switch (checkId) {
                    case R.id.qz:
                        qz_zy_sw_ys = 0;
                        break;

                    case R.id.zy:
                        //执行具体操作
                        qz_zy_sw_ys = 1;
                        break;

                    case R.id.sw:
                        //执行具体操作
                        qz_zy_sw_ys = 2;
                        break;
                    default:
                        break;

                }
            }
        });
        return rootView;
    }
}