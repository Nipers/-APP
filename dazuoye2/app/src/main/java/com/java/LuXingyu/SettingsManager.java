package com.java.LuXingyu;

import android.os.AsyncTask;

public class SettingsManager {
    private static SettingsManager Instance;
    private final SettingsDao settingsDao;
    private final AppDataBase DataBase;

    private SettingsManager(AppDataBase DB) {
        this.DataBase = DB;
        this.settingsDao = this.DataBase.settingsDao();
    }
    public static SettingsManager getInstance(AppDataBase DB) {
        if(Instance == null) {
            Instance = new SettingsManager(DB);
        }
        return Instance;
    }
    private class UpdateSettings extends AsyncTask<Settings, Void, Void> {
        @Override
        protected Void doInBackground(Settings... settings){
            settingsDao.update(settings);
            return null;
        }
    }
    public void update(Settings... settings) {
        try{
            UpdateSettings insert = new UpdateSettings();
            insert.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class GetSettings extends AsyncTask<Void, Void, Settings[]> {
        @Override
        protected Settings[] doInBackground(Void... voids) {
            Settings[] settings = settingsDao.getAllSettings();
            return settings;
        }
    }

    public Settings[] getSettings() {//返回数据库中所有的新闻
        try{
            GetSettings getSettings = new GetSettings();
            return getSettings.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private class InsertSettings extends AsyncTask<Settings, Void, Void> {
        @Override
        protected Void doInBackground(Settings... settings){
            settingsDao.insert(settings);
            return null;
        }
    }
    public void insertSettings(Settings... settings) {
        try{
            InsertSettings insert = new InsertSettings();
            insert.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, settings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
