package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.room.*;

import com.json.JSONArray;
import com.json.JSONObject;

@Entity(tableName = "News")
@TypeConverters({StringConverter.class})
public class News {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    private String aminer_id;
    private String[] authors;
    private String content;
    private String date;
    //private Entities[] entities;
    //private geoInfo[] geoinfos;
    private double influence;
    private String type;
    private boolean isRead = false;
    private boolean isFavorite = false;
    private String source;
    private String title;
    private String[] urls;

    public String getId() { return id; }
    public double getInfluence() {return influence; }
    //public Entities[] getEntities() { return entities; }
    //public geoInfo[] getGeoinfos() { return geoinfos; }
    public String getContent() { return content; }
    public String getAminer_id() { return aminer_id; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public String[] getAuthors() {return authors; }
    public boolean isFavorite() { return isFavorite; }
    public boolean isRead() { return isRead; }
    public String getSource(){return source;}
    public String getTitle(){return title;}

    public void setAminer_id(String aminer_id) {this.aminer_id = aminer_id;}
    public void setAuthors(String[] authors) { this.authors = authors; }
    public void setContent(String content) { this.content = content; }
    public void setDate(String date) { this.date = date; }
    //public void setEntities(Entities[] entities) { this.entities = entities; }
    //public void setGeoinfos(geoInfo[] geoinfos) { this.geoinfos = geoinfos; }
    public void setId(String id) { this.id = id; }
    public void setInfluence(double influence) { this.influence = influence; }
    public void setType(String type) { this.type = type; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setRead(boolean read) { isRead = read; }
    public void setSource(String source){this.source = source;}
    public void setTitle(String title){this.title = title;}

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }

    News() {}
    News(JSONObject json) {
        id = json.getString("_id");
        content = json.getString("content");
        date = json.getString("date");
        JSONArray e = json.getJSONArray("entities");
//        entities = new Entities[e.length()];
//        for (int i = 0; i < e.length(); i++) {
//            JSONObject entity = (JSONObject)e.get(i);
//            entities[i] = new Entities(entity);
//        }
        e = json.getJSONArray("geoInfo");
        //geoinfos = new geoInfo[e.length()];
//        for (int i = 0; i < e.length();i++) {
//            JSONObject geo = (JSONObject) e.get(i);
//            geoinfos[i] = new geoInfo(geo);
//        }
        try {
            influence = json.getDouble("influence");
        }catch(Exception ex){}
        try{
            source = json.getString("source");
        }catch (Exception x){
        }
        title = json.getString("title");
        type = json.getString("type");
        e = json.getJSONArray("urls");
        urls = new String[e.length()];
        for (int i = 0; i < e.length(); i++) {
            urls[i] = e.get(i).toString();
        }
    }
}
//class Entities {
//    private String label;
//    private String url;
//    Entities(JSONObject json) {
//        label = json.getString("label");
//        url = json.getString("url");
//    }
//    Entities(){}
//    public String getLabel() {
//        return label;
//    }
//    public String getUrl(){
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public void setLabel(String label) {
//        this.label = label;
//    }
//
//    @Override
//    public String toString() {
//        return label + url;
//    }
//}
//class geoInfo{
//    private String geoName;
//    private String originText;
//    private double latitude;
//    private double longitude;
//    geoInfo (JSONObject json) {
//        geoName = json.getString("gepoName");
//        originText = json.getString("originText");
//        latitude = json.getDouble("latitude");
//        longitude = json.getDouble("longitude");
//    }
//    public double getLatitude() {return latitude;}
//    public double getLongitude() {return longitude; }
//    public String getGeoName() {return geoName; }
//    public String getOriginText() {return originText; }
//}