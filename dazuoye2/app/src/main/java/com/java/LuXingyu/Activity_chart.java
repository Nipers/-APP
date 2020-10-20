package com.java.LuXingyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity_chart extends AppCompatActivity {
    private ImageButton button;
    private ChartView chart;
    private TextView textView;
    String[] Province = {"香港","新疆","北京","四川","甘肃","上海","广东","台湾","河北","陕西","山西","云南","重庆"
            ,"内蒙古","山东","浙江","天津","辽宁","福建","江苏","海南","澳门","吉林","湖北","江西","黑龙江","安徽"
            ,"贵州","湖南","河南","广西","宁夏","青海","西藏"};
    String[] Country = {"阿根廷","澳大利亚","巴西","加拿大","中国","埃及","德国","希腊","印度","意大利",
            "日本","墨西哥","蒙古","秘鲁","俄罗斯","英国","美国"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


        chart=(ChartView)findViewById(R.id.chart);
        button=(ImageButton)findViewById(R.id.button_chart_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView=(TextView)findViewById(R.id.di_ming);


        Intent intent=getIntent();
        if(intent.getIntExtra("province0_country1",0)==0){
            //从province过来
            int position=intent.getIntExtra("item",0);
            textView.setText(Province[position]);
            int qz=intent.getIntExtra("qz",0);
            ChartView myView=(ChartView)this.findViewById(R.id.chart);
            String[] province={"China|Hong Kong","China|Xinjiang", "China|Beijing", "China|Sichuan",
                    "China|Gansu", "China|Shanghai", "China|Guangdong", "China|Taiwan", "China|Hebei",
                    "China|Shaanxi", "China|Shanxi", "China|Yunnan", "China|Chongqing", "China|Inner Mongol",
                    "China|Shandong", "China|Zhejiang", "China|Tianjin", "China|Liaoning", "China|Fujian",
                    "China|Jiangsu", "China|Hainan", "China|Macao", "China|Jilin", "China|Hubei", "China|Jiangxi",
                    "China|Heilongjiang", "China|Anhui", "China|Guizhou", "China|Hunan", "China|Henan",
                    "China|Guangxi", "China|Ningxia", "China|Qinghai", "China|Xizang"};
            String[] country = {"Argentina","Australia","Brazil","Canada","China","Egypt","Germany","Greece","India","Italy","Japan",
                    "Mexico","Mongolia","Peru","Russia","United Kingdom","United States of America"};
            RegDataManager regDataManager=AppManager.getRegDataManager();
            RegData regData=regDataManager.getProvinceData(province[position]);
                //int month=(regData.getBegin().charAt(5)-'0')*10+(regData.getBegin().charAt(6)-'0');
            if(qz==0) {
                int today = regData.getConfirmed().length - 1;
                int max = regData.getConfirmed()[today];
                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getConfirmed()[today - 60].toString(),
                                regData.getConfirmed()[today - 50].toString(),
                                regData.getConfirmed()[today - 40].toString(),
                                regData.getConfirmed()[today - 30].toString(),
                                regData.getConfirmed()[today - 20].toString(),
                                regData.getConfirmed()[today - 10].toString(),
                                regData.getConfirmed()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒确诊折线图");
            }
            else if(qz==1){
                int today = regData.getCured().length - 1;
                int max = regData.getCured()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getCured()[today - 60].toString(),
                                regData.getCured()[today - 50].toString(),
                                regData.getCured()[today - 40].toString(),
                                regData.getCured()[today - 30].toString(),
                                regData.getCured()[today - 20].toString(),
                                regData.getCured()[today - 10].toString(),
                                regData.getCured()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒治愈折线图");
            }else if(qz==2){
                int today = regData.getDead().length - 1;
                int max = regData.getDead()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getDead()[today - 60].toString(),
                                regData.getDead()[today - 50].toString(),
                                regData.getDead()[today - 40].toString(),
                                regData.getDead()[today - 30].toString(),
                                regData.getDead()[today - 20].toString(),
                                regData.getDead()[today - 10].toString(),
                                regData.getDead()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒死亡折线图");
            }else if(qz==3){
                int today = regData.getSuspected().length - 1;
                int max = regData.getSuspected()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getSuspected()[today - 60].toString(),
                                regData.getSuspected()[today - 50].toString(),
                                regData.getSuspected()[today - 40].toString(),
                                regData.getSuspected()[today - 30].toString(),
                                regData.getSuspected()[today - 20].toString(),
                                regData.getSuspected()[today - 10].toString(),
                                regData.getSuspected()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒疑似折线图");
            }
        }
        else {
            //从country过来
            int position = intent.getIntExtra("item", 0);
            textView.setText(Country[position]);
            int qz=intent.getIntExtra("qz",0);
            ChartView myView = (ChartView) this.findViewById(R.id.chart);

            String[] province = {"China|Hong Kong", "China|Xinjiang", "China|Beijing", "China|Sichuan",
                    "China|Gansu", "China|Shanghai", "China|Guangdong", "China|Taiwan", "China|Hebei",
                    "China|Shaanxi", "China|Shanxi", "China|Yunnan", "China|Chongqing", "China|Inner Mongol",
                    "China|Shandong", "China|Zhejiang", "China|Tianjin", "China|Liaoning", "China|Fujian",
                    "China|Jiangsu", "China|Hainan", "China|Macao", "China|Jilin", "China|Hubei", "China|Jiangxi",
                    "China|Heilongjiang", "China|Anhui", "China|Guizhou", "China|Hunan", "China|Henan",
                    "China|Guangxi", "China|Ningxia", "China|Qinghai", "China|Xizang"};
            String[] country = {"Argentina", "Australia", "Brazil", "Canada", "China", "Egypt", "Germany", "Greece", "India", "Italy", "Japan",
                    "Mexico", "Mongolia", "Peru", "Russia", "United Kingdom", "United States of America"};
            RegDataManager regDataManager = AppManager.getRegDataManager();
            RegData regData = regDataManager.getCountryData(country[position]);
            if(qz==0) {
                int today = regData.getConfirmed().length - 1;
                int max = regData.getConfirmed()[today];
                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getConfirmed()[today - 60].toString(),
                                regData.getConfirmed()[today - 50].toString(),
                                regData.getConfirmed()[today - 40].toString(),
                                regData.getConfirmed()[today - 30].toString(),
                                regData.getConfirmed()[today - 20].toString(),
                                regData.getConfirmed()[today - 10].toString(),
                                regData.getConfirmed()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒确诊折线图");
            }
            else if(qz==1){
                int today = regData.getCured().length - 1;
                int max = regData.getCured()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getCured()[today - 60].toString(),
                                regData.getCured()[today - 50].toString(),
                                regData.getCured()[today - 40].toString(),
                                regData.getCured()[today - 30].toString(),
                                regData.getCured()[today - 20].toString(),
                                regData.getCured()[today - 10].toString(),
                                regData.getCured()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒治愈折线图");
            }else if(qz==2){
                int today = regData.getDead().length - 1;
                int max = regData.getDead()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getDead()[today - 60].toString(),
                                regData.getDead()[today - 50].toString(),
                                regData.getDead()[today - 40].toString(),
                                regData.getDead()[today - 30].toString(),
                                regData.getDead()[today - 20].toString(),
                                regData.getDead()[today - 10].toString(),
                                regData.getDead()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒死亡折线图");
            }else if(qz==3){
                int today = regData.getSuspected().length - 1;
                int max = regData.getSuspected()[today];

                myView.SetInfo(new String[]{"第10天", "第20天", "第30天", "第40天", "第50天",
                                "第60天", "今天"}, // X轴刻度 日期
                        new String[]{"", String.valueOf(max / 4), String.valueOf(2 * max / 4), String.valueOf(3 * max / 4), String.valueOf(max), String.valueOf(5*max/4)}, // Y轴刻度
                        new String[]{regData.getSuspected()[today - 60].toString(),
                                regData.getSuspected()[today - 50].toString(),
                                regData.getSuspected()[today - 40].toString(),
                                regData.getSuspected()[today - 30].toString(),
                                regData.getSuspected()[today - 20].toString(),
                                regData.getSuspected()[today - 10].toString(),
                                regData.getSuspected()[today].toString(),
                        }, // 数据 感染数量
                        "近70天病毒疑似折线图");
            }
        }
    }
}