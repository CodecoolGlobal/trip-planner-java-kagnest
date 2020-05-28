package com.codecool.tripplanner.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DestinationRepository {

    private DestinationDao destDao;
    private LiveData<List<Destination>> destinationList;

    public DestinationRepository(Application application) {
        TripPlannerDatabase db = TripPlannerDatabase.getInstance(application);
        destDao = db.destinationDao();
        destinationList = destDao.getAllDestinations();
    }

    public LiveData<List<Destination>> getDestinationList() {
        return destinationList;
    }

    void insert(Destination destination){
        TripPlannerDatabase.databaseWriteExecutor.execute(()->{
            destDao.insert(destination);
        });
    }
}
