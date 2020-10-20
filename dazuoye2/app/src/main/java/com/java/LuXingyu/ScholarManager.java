package com.java.LuXingyu;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;

public class ScholarManager {
    private static ScholarManager Instance = null;
    private final ScholarDao scholarDao;
    private final AppDataBase appDB;

    private ScholarManager(AppDataBase appDB){
        this.appDB = appDB;
        this.scholarDao = this.appDB.ScholarDao();
    }

    public static ScholarManager getInstance(AppDataBase appDB){
        if(Instance == null){
            Instance = new ScholarManager(appDB);
        }
        return Instance;
    }

    public Scholar[] getScholar(String url){//从URL读取疫情学者信息
        Scholar Scholars[] = new Scholar[0];
        try{
            Service.getURLResponceTask g = new Service.getURLResponceTask();
            Service.ParseScholar p = new Service.ParseScholar();
            String data = g.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url).get();
            Scholars = p.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, data).get();
            insertScholar(Scholars);//将学者导入数据库
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return Scholars;
    }

    private class InsertScholarTask extends AsyncTask<Scholar, Void, Void>{
        @Override
        protected Void doInBackground(Scholar... Scholars){
            scholarDao.insert(Scholars);
            return null;
        }
    }

    public void insertScholar(Scholar... yiqingScholars){
        try {
            InsertScholarTask insertScholarTask = new InsertScholarTask();
            insertScholarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,yiqingScholars);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private class GetOneTypeScholarTask extends AsyncTask<Boolean, Void, ScholarIntroduction[]>{
        @Override
        protected  ScholarIntroduction[] doInBackground(Boolean... params){
            Scholar Scholars[] = scholarDao.getOneTypeScholar(params[0]);
            ScholarIntroduction[] result= new ScholarIntroduction[Scholars.length];
            for(int i = 0; i < Scholars.length; i++){
                result[i] = new ScholarIntroduction(Scholars[i]);
            }
            return result;
        }
    }

    public ArrayList<ScholarIntroduction> getOneTypeScholar(Boolean ifpassedaway){
        try {
            GetOneTypeScholarTask getScholarTask = new GetOneTypeScholarTask();
            return new ArrayList<ScholarIntroduction>(Arrays.asList(getScholarTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, ifpassedaway).get()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Scholar[] getScholarByID(String id){
        try {
            GetScholarByIDTask getScholarByIDTask = new GetScholarByIDTask();
            return getScholarByIDTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id).get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private class GetScholarByIDTask extends AsyncTask<String, Void, Scholar[]>{
        @Override
        protected  Scholar[] doInBackground(String... params){
            return scholarDao.getScholarByID(params[0]);
        }
    }
}
