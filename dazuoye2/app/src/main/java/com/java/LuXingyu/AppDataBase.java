package com.java.LuXingyu;

import android.content.Context;
import androidx.room.*;
@Dao
interface NewsDao{
    @Query("SELECT * FROM News")
    News[] getAllNews();

    @Query("SELECT * FROM News WHERE type = :type")
    News[] getOneTypeOfNews(String type);

    @Query("SELECT * FROM News WHERE title LIKE '%' || :target || '%'")
    News[] getSearchedNews(String target);

    @Update
    void update(News... news);
//
//    @Query("UPDATE News SET isFavorite = :isFavorite")
//    void updateIsFavorite(Boolean isFavorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News... news);

    @Query("SELECT * FROM News WHERE isRead = :isread")
    News[] getReadNews(Boolean isread);

    @Query("SELECT * FROM News WHERE isFavorite = :isfavorite")
    News[] getFavoriteNews(Boolean isfavorite);

//
//    @Delete
//    void delete(News...news);

    @Query("SELECT * FROM news WHERE id = :param")
    News[] getNewsById(String param);
}

@Dao
interface RegDataDao{
    @Query("SELECT * FROM RegData")
    RegData[] getAllRegData();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RegData... regData);

    @Query("SELECT * FROM RegData WHERE Loc = :province")
    RegData[] getProvinceData(String province);

    @Query("SELECT * FROM RegData WHERE Loc = :Country")
    RegData[] getCountryData(String Country);
}

@Dao
interface ScholarDao{
    @Query("SELECT * FROM Scholar")
    Scholar[] getAllScholar();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scholar... Scholars);

    @Query("SELECT * FROM Scholar WHERE id = :id")
    Scholar[] getScholarByID(String id);

    @Query("SELECT * FROM Scholar WHERE is_passedaway = :passedaway")
    Scholar[] getOneTypeScholar(Boolean passedaway);
}

@Dao
interface ReadIDDao {
    @Query("SELECT * FROM ReadID")
    ReadID[] getAllReadID();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReadID... readIDS);
}

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    History[] getAllHistory();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(History... histories);
}

@Dao
interface SettingsDao {
    @Query("SELECT * FROM Settings")
    Settings[] getAllSettings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Settings... categories);

    @Update
    void update(Settings... categories);
}
@Database(entities = {News.class, RegData.class, Scholar.class, ReadID.class, History.class, Settings.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{
    private static AppDataBase Instance = null;
    private static final Object sLock = new Object();
    public abstract NewsDao newsDao();
    public abstract RegDataDao regDataDao();
    public abstract ScholarDao ScholarDao();
    public abstract ReadIDDao readIDDao();
    public abstract HistoryDao historyDao();
    public abstract SettingsDao settingsDao();

    public static AppDataBase getInstance(Context context) {
        synchronized (sLock) {
            if(Instance == null)
                Instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "zhanyi.db").build();
            return Instance;
        }
    }
}
