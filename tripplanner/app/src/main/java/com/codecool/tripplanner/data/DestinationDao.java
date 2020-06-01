package com.codecool.tripplanner.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface DestinationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Destination destination);

    @Query("SELECT * FROM dest_table ORDER BY dest_name ASC")
    List<Destination> getAllDestinations();

    @Query("SELECT * FROM dest_table WHERE id = :dest_id")
    Destination getDestinationById(String dest_id);

    @Query("DELETE FROM dest_table")
    void deleteAll();
}
