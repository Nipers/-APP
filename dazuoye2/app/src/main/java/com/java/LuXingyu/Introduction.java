package com.java.LuXingyu;

public class Introduction {

    private String title;
    private String source;
    private String date;
    private String ID;

    public Introduction(News news) {
        this.title = news.getTitle();
        this.source = news.getSource();
        this.date = news.getDate();
        this.ID = news.getId();
    }
    public Introduction(String title, String source, String date) {
        this.title = title;
        this.source = source;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.source = from;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }
}
