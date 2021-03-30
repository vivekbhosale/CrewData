package com.example.juststream.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Crew {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Name")
    @NonNull
    public String name;

    @ColumnInfo(name = "Agency")
    public String agency;

    @ColumnInfo(name = "Image")
    public String image;

    @ColumnInfo(name = "Wikipedia")
    public String wikipedia;

    @ColumnInfo(name = "Status")
    public String status;
    @NonNull

    //Getters
    public String getNames() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImage() {
        return image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }

}
