package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ReadID")
public class ReadID {
    @PrimaryKey
    @NonNull
    String ID;
    ReadID(){;}
    ReadID(String id) {
        ID = id;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }
}
