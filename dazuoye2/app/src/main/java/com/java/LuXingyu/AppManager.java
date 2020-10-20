package com.java.LuXingyu;

import android.content.Context;

public class AppManager {
    private static AppManager Instance = null;
    private static AppDataBase appDB = null;
    private AppManager(Context context){
        this.appDB = AppDataBase.getInstance(context);
    }
    public static AppManager getAppManager(Context context){
        if(Instance == null){
            Instance = new AppManager(context);
        }
        return Instance;
    }

    public static NewsManager getNewsManager(){
        return NewsManager.getInstance(appDB);
    }

    public static RegDataManager getRegDataManager(){
        return RegDataManager.getInstance(appDB);
    }
    public static ScholarManager getScholarManager() {
        return ScholarManager.getInstance(appDB);
    }
    public static ReadIDManager getReadIDManager() {return ReadIDManager.getInstance(appDB);}
    public static HistoryManager getHistoryManager() {return HistoryManager.getInstance(appDB);}
    public static SettingsManager getSettingsManager() {return SettingsManager.getInstance(appDB);}
}