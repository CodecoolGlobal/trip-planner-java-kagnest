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
    private String coordinates;

    public Destination(@NonNull String destinationName, @NonNull String url, @NonNull String coordinates) {
        this.destinationName = destinationName;
        this.url = url;
        this.coordinates = coordinates;
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
    public String getCoordinates() {
        return coordinates;
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

    public void setCoordinates(@NonNull String coordinates) {
        this.coordinates = coordinates;
    }
}
