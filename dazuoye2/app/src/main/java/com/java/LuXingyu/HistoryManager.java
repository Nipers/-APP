package com.java.LuXingyu;

import android.os.AsyncTask;

public class HistoryManager {
    private static HistoryManager Instance;
    private final HistoryDao historyDao;
    private final AppDataBase DataBase;

    private HistoryManager(AppDataBase DB) {
        this.DataBase = DB;
        this.historyDao = this.DataBase.historyDao();
    }
    public static HistoryManager getInstance(AppDataBase DB) {
        if(Instance == null) {
            Instance = new HistoryManager(DB);
        }
        return Instance;
    }
    private class InsertHistory extends AsyncTask<History, Void, Void> {
        @Override
        protected Void doInBackground(History... history){
            historyDao.insert(history);
            return null;
        }
    }
    public void insertReadID(History... history) {
        try{
            InsertHistory insert = new InsertHistory();
            insert.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, history);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class GetAllHistory extends AsyncTask<Void, Void, History[]> {
        @Override
        protected History[] doInBackground(Void... voids) {
            History[] histories = historyDao.getAllHistory();
            return histories;
        }
    }

    public History[] getAllHistory() {//返回数据库中所有的搜索历史
        try{
            GetAllHistory getHistory = new GetAllHistory();
            return getHistory.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
