package com.codecool.tripplanner.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dest_table")
public class Destination {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "dest_name")
    private String destinationName;

    @NonNull
    private String url;

    @NonNull
    private String lat;

    @NonNull
    private String longitude;

    public Destination(@NonNull String destinationName, @NonNull String url, @NonNull String lat, @NonNull String longitude) {
        this.destinationName = destinationName;
        this.url = url;
        this.lat = lat;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getDestinationName() {
        return destinationName;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getLat() {
        return lat;
    }

    @NonNull
    public String getLongitude() {
        return longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestinationName(@NonNull String destinationName) {
        this.destinationName = destinationName;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public void setLat(@NonNull String lat) {
        this.lat = lat;
    }

    public void setLongitude(@NonNull String longitude) {
        this.longitude = longitude;
    }

}
