package com.java.LuXingyu;

import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

public class EntityManager {
    private static final String URL = "https://innovaapi.aminer.cn/covid/api/v1/pneumonia/entityquery?entity=";
    private static EntityManager Instance;

    private EntityManager() {}
    public static EntityManager getInstance() {
        if(Instance == null) {
            Instance = new EntityManager();
        }
        return Instance;
    }
    public static PlagueEntity[] getSearchedEntity(String target) {
        PlagueEntity[] entities = null;
        try {
            Service.getURLResponceTask get = new Service.getURLResponceTask();
            Service.ParseEntity parse = new Service.ParseEntity();
            String responce = get.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, URL + target).get();
            entities = parse.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, responce).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
