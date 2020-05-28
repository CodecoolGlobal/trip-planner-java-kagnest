package com.codecool.tripplanner.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dest_table")
public class Destination {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private String id;

    @NonNull
    @ColumnInfo(name = "dest_name")
    private String destinationName;

    @NonNull
    private String url;

    private String coordinates;
}
