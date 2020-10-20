package com.java.LuXingyu;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;

public class NewsManager{
    private static NewsManager Instance;
    private final NewsDao newsDao;
    private final AppDataBase DataBase;

    private NewsManager(AppDataBase DB) {
        this.DataBase = DB;
        this.newsDao = this.DataBase.newsDao();
    }
    public static NewsManager getInstance(AppDataBase DB) {
        if(Instance == null) {
            Instance = new NewsManager(DB);
        }
        return Instance;
    }
    public Introduction[] getNews(String url){//新闻显示界面需要调用的函数
        News[] news = null;
        try {
            Service.getURLResponceTask get = new Service.getURLResponceTask();
            Service.ParseNews parse = new Service.ParseNews();
            String responce = get.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url).get();
            news = parse.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, responce).get();
            insertNews(news);//将从url读取的news填入数据库
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Introduction introduction[] = new Introduction[news.length];
        for (int i = 0; i < news.length; i++) {
            introduction[i] = new Introduction(news[i]);
        }
        return introduction;
    }

    private class InsertNews extends AsyncTask<News, Void, Void> {
        @Override
        protected Void doInBackground(News... news){
            newsDao.insert(news);
            return null;
        }
    }

    public void insertNews(News... news) {
        try{
            InsertNews insert = new InsertNews();
            insert.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, news);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class GetAllNews extends AsyncTask<String, Void, News[]> {
        @Override
        protected News[] doInBackground(String... params) {
            News[] news = newsDao.getAllNews();
            return news;
        }
    }

    public News[] getAllNews() {//返回数据库中所有的新闻
        try{
            GetAllNews getNews = new GetAllNews();
            return getNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetOneTypeNews extends AsyncTask<String, Void, News[]> {
        @Override
        protected News[] doInBackground(String... params) {
            News[] news = newsDao.getOneTypeOfNews(params[0]);
            return news;
        }
    }

    public News[] getOneTypeNews(String type) {//获取某一种类的新闻，包括paper
        try{
            GetOneTypeNews getOneTypeNews = new GetOneTypeNews();
            return getOneTypeNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, type).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetSearchedNews extends AsyncTask<String, Void, News[]> {
        @Override
        protected News[] doInBackground(String... params) {
            News[] news = newsDao.getSearchedNews(params[0]);
            return news;
        }
    }

    public News[] getSearchedNews(String target) {
        try{
            GetSearchedNews getSearchedNews = new GetSearchedNews();
            return getSearchedNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, target).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
    private class UpdateIsReadTask extends AsyncTask<News, Void, Void>{
        @Override
        protected Void doInBackground(News... params){
            newsDao.update(params[0]);
            return null;
        }
    }

    public void updateIsRead(News news){
        try {
            UpdateIsReadTask update = new UpdateIsReadTask();
            update.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, news);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private class UpdateIsFavorite extends AsyncTask<News, Void, Void>{
        @Override
        protected Void doInBackground(News... params){
            newsDao.update(params[0]);
            return null;
        }
    }

    public void updateIsFvorite(News news){
        try {
            UpdateIsFavorite update = new UpdateIsFavorite();
            update.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, news);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private class GetContent extends AsyncTask<String, Void, News[]>{
        @Override
        protected News[] doInBackground(String... params){
            return newsDao.getNewsById(params[0]);
        }
    }

    public News[] getContent (String id) {
        try{
            GetContent getNews = new GetContent();
            return getNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private class GetReadNews extends AsyncTask<Void, Void, News[]> {
        @Override
        protected News[] doInBackground(Void... voids) {
            News[] news = newsDao.getReadNews(true);
            return news;
        }
    }

    public News[] getReadNews() {//获取已读新闻，包括paper
        try{
            GetReadNews getReadNews = new GetReadNews();
            return getReadNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetFavoriteNews extends AsyncTask<Void, Void, News[]> {
        @Override
        protected News[] doInBackground(Void... voids) {
            News[] news = newsDao.getFavoriteNews(true);
            return news;
        }
    }

    public News[] getFavoriteNews() {//获取收藏新闻，包括paper
        try{
            GetFavoriteNews getFavoriteNews = new GetFavoriteNews();
            return getFavoriteNews.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
