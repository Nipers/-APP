package com.java.LuXingyu;

public class ScholarIntroduction {
    private String id;
    private String name;
    private String name_zh;
    private Indice indice;
    private String affiliation;
    private String position;
    private String avatar;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getName_zh() {
        return name_zh;
    }

    public Indice getIndice() {
        return indice;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getPosition() {
        return position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh;
    }

    public void setIndice(Indice indice) {
        this.indice = indice;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    ScholarIntroduction(Scholar scholar){
        this.id = scholar.getId();
        this.name = scholar.getName();
        this.name_zh = scholar.getName_zh();
        this.indice = scholar.getIndices();
        this.affiliation = scholar.getProfile().getAffiliation();
        this.position = scholar.getProfile().getPosition();
        this.avatar = scholar.getAvatar();
    }
}
