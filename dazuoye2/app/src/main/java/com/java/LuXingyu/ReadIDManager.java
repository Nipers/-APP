package com.java.LuXingyu;

import android.os.AsyncTask;

public class ReadIDManager {
    private static ReadIDManager Instance;
    private final ReadIDDao readIDDao;
    private final AppDataBase DataBase;

    private ReadIDManager(AppDataBase DB) {
        this.DataBase = DB;
        this.readIDDao = this.DataBase.readIDDao();
    }
    public static ReadIDManager getInstance(AppDataBase DB) {
        if(Instance == null) {
            Instance = new ReadIDManager(DB);
        }
        return Instance;
    }
    private class InsertReadID extends AsyncTask<ReadID, Void, Void> {
        @Override
        protected Void doInBackground(ReadID... readIDS){
            readIDDao.insert(readIDS);
            return null;
        }
    }
    public void insertReadID(ReadID... readIDS) {
        try{
            InsertReadID insert = new InsertReadID();
            insert.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, readIDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class GetAllReadID extends AsyncTask<Void, Void, ReadID[]> {
        @Override
        protected ReadID[] doInBackground(Void... voids) {
            ReadID[] readIDS = readIDDao.getAllReadID();
            return readIDS;
        }
    }

    public ReadID[] getAllReadID() {//返回数据库中所有的新闻
        try{
            GetAllReadID getReadID = new GetAllReadID();
            return getReadID.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
