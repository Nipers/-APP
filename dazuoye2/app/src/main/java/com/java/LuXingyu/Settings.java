package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@TypeConverters({StringConverter.class, booleanConverter.class})
@Entity(tableName = "Settings")
public class Settings {
    @PrimaryKey
    @NonNull
    private int id = 1;
    private String[] category;
    public boolean[] visible;
    private Boolean Initilized;

    Settings(boolean[] visible, String[] category) {
        this.category = category;
        this.visible = visible;
    }
    public void setVisible(boolean[] visible) {
        this.visible = visible;
    }
    public boolean[] getVisible(){
        return visible;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public Boolean getInitilized() {
        return Initilized;
    }

    public void setInitilized(Boolean initilized) {
        Initilized = initilized;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
