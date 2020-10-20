package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "History")
public class History {
    @PrimaryKey
    @NonNull
    String content;
    History(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent(){
        return content;
    }
}
