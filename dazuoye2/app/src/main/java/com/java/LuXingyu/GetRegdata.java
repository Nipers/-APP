package com.java.LuXingyu;

import com.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

public class GetRegdata implements Runnable{
    private RegDataManager manager;
    private Thread thread;
    private final String url_name = "https://covid-dashboard.aminer.cn/api/dist/epidemic.json";
    public GetRegdata(RegDataManager regDataManager){
        this.manager = regDataManager;
    }
    @Override
    public void run() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(url_name);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(15 * 1000);
            conn.connect();
            if (conn.getResponseCode() != 200) {
                System.out.println("Fail to get connection");
            }
        }catch (MalformedInputException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try{
            inputStream = conn.getInputStream();
        }catch (IOException e){}
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            JSONObject json = new JSONObject(new String(data, "UTF-8"));
            String[] province={"China|Hong Kong","China|Xinjiang", "China|Beijing", "China|Sichuan",
                    "China|Gansu", "China|Shanghai", "China|Guangdong", "China|Taiwan", "China|Hebei",
                    "China|Shaanxi", "China|Shanxi", "China|Yunnan", "China|Chongqing", "China|Inner Mongol",
                    "China|Shandong", "China|Zhejiang", "China|Tianjin", "China|Liaoning", "China|Fujian",
                    "China|Jiangsu", "China|Hainan", "China|Macao", "China|Jilin", "China|Hubei", "China|Jiangxi",
                    "China|Heilongjiang", "China|Anhui", "China|Guizhou", "China|Hunan", "China|Henan",
                    "China|Guangxi", "China|Ningxia", "China|Qinghai", "China|Xizang"};
            String[] country = {"Argentina","Australia","Brazil","Canada","China","Egypt","Germany","Greece","India","Italy","Japan",
                    "Mexico","Mongolia","Peru","Russia","United Kingdom","United States of America"};
            RegData[] result = new RegData[province.length+country.length];
            for (int i = 0; i < province.length; i++) {
                result[i] = new RegData(json.getJSONObject(province[i]), province[i]);
            }
            for (int i = 0; i < country.length; i++) {
                result[i + province.length] = new RegData(json.getJSONObject(country[i]), country[i]);
            }
            manager.insertRegData(result);
        }catch(IOException e) {}
        finally{
            try {
                outStream.close();
            }catch(IOException e){}
            try {
                inputStream.close();
            }catch (IOException e){}
        }
        conn.disconnect();
    }
    public void start() {
        if (thread == null)
            thread = new Thread(this, "GETDATA");
        thread.start();
    }
}
