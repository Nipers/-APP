package com.java.LuXingyu;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.json.JSONObject;

@Entity(tableName = "Scholar")
@TypeConverters({StringConverter.class, ProfileConverter.class, IndiceConverter.class, EmailUConverter.class, DoubleConverter.class})
public class Scholar {
    String avatar;
    @NonNull
    @PrimaryKey
    private String id;
    private Indice indice;
    private String name;
    private String name_zh;
    private Integer num_followed;
    private Integer num_viewed;
    private Profile profile;
    private Integer score;
    private String sourcetype;
    private String[] tags;
    private Double[] tags_score;
    private Integer index;
    private Integer tab;
    private Boolean is_passedaway;
    Scholar(JSONObject json){
        avatar = json.getString("avatar");
        id = json.getString("id");
        indice = new Indice(json.getJSONObject("indices"));
        name = json.getString("name");
        name_zh = json.getString("name_zh");
        num_followed = json.getInt("num_followed");
        num_viewed = json.getInt("num_viewed");
        profile = new Profile(json.getJSONObject("profile"));
        is_passedaway = json.getBoolean("is_passedaway");
    }
    Scholar(){}
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public void setId(String id) { this.id = id; }
    public void setIndice(Indice indice) { this.indice = indice; }
    public void setName(String name) { this.name = name; }
    public void setName_zh(String name_zh) { this.name_zh = name_zh; }
    public void setNum_followed(Integer num_followed) { this.num_followed = num_followed; }
    public void setNum_viewed(Integer num_viewed) { this.num_viewed = num_viewed; }
    public void setProfile(Profile profile) { this.profile = profile; }
    public void setScore(Integer score) { this.score = score; }
    public void setSourcetype(String sourcetype) { this.sourcetype = sourcetype;}
    public void setTags(String[] tags) { this.tags = tags; }
    public void setTags_score(Double[] tags_score) { this.tags_score = tags_score; }
    public void setIndex(Integer index) { this.index = index; }
    public void setTab(Integer tab) { this.tab = tab; }
    public void setIs_passedaway(Boolean is_passedaway) { this.is_passedaway = is_passedaway; }
    public String getAvatar() {return avatar; }
    public String getId() { return id; }
    public Indice getIndices() { return indice; }
    public String getName() { return name;}
    public String getName_zh() { return name_zh; }
    public Integer getNum_followed() { return num_followed;}public Integer getNum_viewed() { return num_viewed; }
    public Profile getProfile() { return profile; }
    public Integer getScore() { return score; }
    public String getSourcetype() { return sourcetype; }
    public String[] getTags() { return tags; }
    public Double[] getTags_score() { return tags_score; }
    public Integer getIndex() { return index; }
    public Integer getTab() { return tab; }
    public Boolean getIs_passedaway() { return is_passedaway; }
    public Indice getIndice() {return indice;}
}

class Indice{
    private Double activity;
    private Integer citations;
    private Double diversity;
    private Integer gindex;
    private Integer hindex;
    private Double newStar;
    private Integer pubs;
    private Double risingStar;
    private Double sociability;

    public Indice(JSONObject indice) {
        activity = indice.getDouble("activity");
        citations = indice.getInt("citations");
        diversity = indice.getDouble("diversity");
        gindex = indice.getInt("gindex");
        hindex = indice.getInt("hindex");
        newStar = indice.getDouble("newStar");
        pubs = indice.getInt("pubs");
        risingStar = indice.getDouble("risingStar");
        sociability = indice.getDouble("sociability");
    }

    public void setActivity(Double activity) { this.activity = activity; }
    public void setCitations(Integer citations) { this.citations = citations; }
    public void setDiversity(Double diversity) { this.diversity = diversity; }
    public void setGindex(Integer gindex) { this.gindex = gindex; }
    public void setHindex(Integer hindex) { this.hindex = hindex; }
    public void setNewStar(Double newStar) { this.newStar = newStar; }
    public void setPubs(Integer pubs) { this.pubs = pubs; }
    public void setRisingStar(Double risingStar) { this.risingStar = risingStar; }
    public void setSociability(Double sociability) { this.sociability = sociability; }
    public Double getActivity() { return activity; }
    public Integer getCitations() { return citations; }
    public Double getDiversity() { return diversity; }
    public Integer getGindex() { return gindex;}
    public Integer getHindex() {return hindex;}
    public Double getNewStar() {return newStar;}
    public Integer getPubs() {return pubs;}
    public Double getRisingStar() {return risingStar;}
    public Double getSociability() {return sociability;}

    @Override
    public String toString() {
        return "Indice{" +
                "activity=" + activity +
                ", citations=" + citations +
                ", diversity=" + diversity +
                ", gindex=" + gindex +
                ", hindex=" + hindex +
                ", newStar=" + newStar +
                ", pubs=" + pubs +
                ", risingStar=" + risingStar +
                ", sociability=" + sociability +
                '}';
    }
}


class EmailsU{
    private String address;
    private String src;
    private Double weight;

    public EmailsU(JSONObject json) {
        address = json.getString("address");
        src = json.getString("src");
        weight = json.getDouble("weight");
    }

    public void setAddress(String address) { this.address = address; }
    public void setSrc(String src) { this.src = src; }
    public void setWeight(Double weight) { this.weight = weight; }
    public String getAddress() { return address; }
    public String getSrc() { return src; }
    public Double getWeight() { return weight; }

    @Override
    public String toString() {
        return "EmailsU{" +
                "address='" + address + '\'' +
                ", src='" + src + '\'' +
                ", weight=" + weight +
                '}';
    }
}