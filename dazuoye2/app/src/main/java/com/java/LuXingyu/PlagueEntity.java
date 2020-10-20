package com.java.LuXingyu;

import java.util.Arrays;

import com.json.JSONArray;
import com.json.JSONObject;
public class PlagueEntity {
    String img;
    String[] related_entities;
    String[] relationships;
    boolean[] forward;
    String info;
    String label;
    double hot;
    String[] properties;
    public PlagueEntity(JSONObject entity) {
        label = entity.getString("label");
        img = entity.get("img").toString();
        hot = entity.getDouble("hot");
        JSONArray related = entity.getJSONObject("abstractInfo").getJSONObject("COVID").getJSONArray("relations");
        int number = related.length();
        related_entities = new String[number];
        relationships = new String[number];
        forward = new boolean[number];
        info = entity.getJSONObject("abstractInfo").getString("baidu");
        for (int i = 0; i < number; i++) {
            JSONObject related_entity = related.getJSONObject(i);
            related_entities[i] = related_entity.getString("label");
            relationships[i] = related_entity.getString("relation");
            forward[i] = related_entity.getBoolean("forward");
        }
        JSONObject covid = entity.getJSONObject("abstractInfo").getJSONObject("COVID");
        JSONObject PRO = covid.getJSONObject("properties");
        String pro = PRO.toString();
        pro = pro.substring(1, pro.length() - 1);
        String[] pros = pro.split(",");
        properties = new String[pros.length];
        for (int i = 0; i < pros.length; i++) {
            StringBuilder s = new StringBuilder();
            String parts[] = pros[i].split("\"");
            for (String x : parts) {
                s.append(x);
            }
            properties[i] = s.toString();
        }
    }

    public void setRelationships(String[] relationships) {this.relationships = relationships;}
    public void setRelated_entities(String[] related_entities) { this.related_entities = related_entities; }
    public void setInfo(String info) { this.info = info; }
    public void setImg(String img) { this.img = img; }
    public void setHot(Double hot) { this.hot = hot; }
    public void setForward(boolean[] forward) { this.forward = forward; }
    public String[] getRelationships() { return relationships; }
    public String[] getRelated_entities() { return related_entities; }
    public String getInfo() { return info; }
    public String getImg() {return img; }
    public Double getHot() { return hot; }
    public boolean[] getForward() { return forward; }
    public String[] getProperties(){return properties;}
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "PlagueEntity{" +
                "img='" + img + '\'' +
                ", related_entities=" + Arrays.toString(related_entities) +
                ", relationships=" + Arrays.toString(relationships) +
                ", forward=" + Arrays.toString(forward) +
                ", info='" + info + '\'' +
                ", hot=" + hot +
                '}';
    }
}

