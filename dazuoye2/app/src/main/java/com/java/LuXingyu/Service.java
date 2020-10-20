package com.java.LuXingyu;

import android.os.AsyncTask;

import com.json.JSONArray;
import com.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

public class Service {
    static class getURLResponceTask extends AsyncTask<String, Void, String> {//Read All URL's content
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder URL_string = new StringBuilder();
            for (String s: strings)
                URL_string.append(s);
            HttpURLConnection conn = null;
            System.out.println(URL_string);
            try {
                URL url = new URL(URL_string.toString());
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
                String result = new String(data, "UTF-8");
                return result;
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
            return "";
        }
    }
    static class ParseNews extends AsyncTask<String, Void, News[]> {//Parse News from String;
        @Override
        protected News[] doInBackground(String... strings) {
            JSONObject json = null;
            ArrayList<News> ans = new ArrayList<>();
            if (strings[0] != null) {
                json = new JSONObject(strings[0]);
                JSONArray array = json.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    ans.add(new News((JSONObject) array.get(i)));
                }
            }
            News[] result = new News[ans.size()];
            return ans.toArray(result);
        }
    }
    static class ParseRegData extends AsyncTask<JSONObject, Void, RegData[]> {//Parse RegData from string;
        @Override
        protected RegData[] doInBackground(JSONObject... json) {
            RegData[] result = null;
            if (json[0] != null) {
                String[] province={"China|Hong Kong","China|Xinjiang", "China|Beijing", "China|Sichuan",
                        "China|Gansu", "China|Shanghai", "China|Guangdong", "China|Taiwan", "China|Hebei",
                        "China|Shaanxi", "China|Shanxi", "China|Yunnan", "China|Chongqing", "China|Inner Mongol",
                        "China|Shandong", "China|Zhejiang", "China|Tianjin", "China|Liaoning", "China|Fujian",
                        "China|Jiangsu", "China|Hainan", "China|Macao", "China|Jilin", "China|Hubei", "China|Jiangxi",
                        "China|Heilongjiang", "China|Anhui", "China|Guizhou", "China|Hunan", "China|Henan",
                        "China|Guangxi", "China|Ningxia", "China|Qinghai", "China|Xizang"};
                String[] country = {"Australia","Brazil","Canada","China","Egypt","Germany","Greece","India","Japan",
                        "Mexico","Russia","United Kingdom","United States of America"};
                result = new RegData[province.length+country.length];
                for (int i = 0; i < province.length; i++) {
                    result[i] = new RegData(json[0].getJSONObject(province[i]), province[i]);
                }
                for (int i = 0; i < country.length; i++) {
                    result[i + province.length] = new RegData(json[0].getJSONObject(country[i]), country[i]);
                }
            }
            return result;
        }
    }
    static class ParseScholar extends AsyncTask<String, Void, Scholar[]> {//Parse Scholar from string;
        @Override
        protected Scholar[] doInBackground(String... strings) {
            Scholar[] result = null;
            if (strings[0] != null) {
                JSONObject data = new JSONObject(strings[0]);
                JSONArray scholars = data.getJSONArray("data");
                result = new Scholar[scholars.length()];
                for (int i = 0; i < scholars.length(); i++) {
                    result[i] = new Scholar((JSONObject)scholars.get(i));
                }
            }
            return result;
        }
    }
    static class ParseEntity extends AsyncTask<String, Void, PlagueEntity[]> {//Parse Scholar from string;
        @Override
        protected PlagueEntity[] doInBackground(String... strings) {
            PlagueEntity[] result = null;
            if (strings[0] != null) {
                JSONObject data = new JSONObject(strings[0]);
                JSONArray entities = data.getJSONArray("data");
                result = new PlagueEntity[entities.length()];
                for (int i = 0; i < entities.length(); i++) {
                    result[i] = new PlagueEntity((JSONObject)entities.get(i));
                }
            }
            return result;
        }
    }

}
