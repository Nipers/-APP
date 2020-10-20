package com.java.LuXingyu;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

public class RegDataManager {
    private static RegDataManager Instance = null;
    private final RegDataDao regDataDao;
    private final AppDataBase database;

    private RegDataManager(AppDataBase DB) {
        this.database = DB;
        this.regDataDao = this.database.regDataDao();
    }
    public static RegDataManager getInstance(AppDataBase appDB){
        if(Instance == null){
            Instance = new RegDataManager(appDB);
        }
        return Instance;
    }

//    public void getRegData(String url) {
//        RegData[] result = null;
//        try {
//            Service.getURLResponceTask get = new Service.getURLResponceTask();
//            Service.ParseRegData parse = new Service.ParseRegData();
//            String data = get.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url).get();
//            result = parse.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data).get();
//            insertRegData(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//    }

    private class InsertData extends AsyncTask<RegData, Void, Void>{
        @Override
        protected Void doInBackground(RegData... regData){
            regDataDao.insert(regData);
            return null;
        }
    }

    public void insertRegData(RegData... regData){
        try {
            InsertData insertDataTask = new InsertData();
            insertDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,regData);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private class GetAllData extends AsyncTask<String, Void, RegData[]>{
        @Override
        protected  RegData[] doInBackground(String... params){
            return regDataDao.getAllRegData();
        }
    }

    public ArrayList<RegData> getAllYiqingData(){
        try {
            GetAllData getAllData = new GetAllData();
            return new ArrayList<RegData>(Arrays.asList(getAllData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"0").get()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public RegData getCountryData(String Country){
        try {
            GetCountryData getRegData = new GetCountryData();
            RegData[] province = getRegData.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Country).get();

            return province[0];
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private class GetCountryData extends AsyncTask<String, Void, RegData[]>{
        @Override
        protected  RegData[] doInBackground(String... params){
            return regDataDao.getCountryData(params[0]);
        }
    }
    public RegData getProvinceData(String Province){
        try {
            GetProvinceData getRegDataTask = new GetProvinceData();
            RegData[] province = getRegDataTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Province).get();
            return province[0];
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private class GetProvinceData extends AsyncTask<String, Void, RegData[]>{
        @Override
        protected  RegData[] doInBackground(String... params){
            return regDataDao.getProvinceData(params[0]);
        }
    }


}
