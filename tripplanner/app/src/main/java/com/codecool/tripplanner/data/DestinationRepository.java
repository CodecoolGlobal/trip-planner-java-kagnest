package com.codecool.tripplanner.data;

import android.app.Application;

import java.util.List;


public class DestinationRepository {

    private DestinationDao destDao;

    public DestinationRepository(Application application) {
        TripPlannerDatabase db = TripPlannerDatabase.getInstance(application);
        destDao = db.destinationDao();
    }

    public void insert(Destination destination){
        TripPlannerDatabase.databaseWriteExecutor.execute(()->{
            destDao.insert(destination);
        });
    }

    public List<Destination> getDestinationList() {
        return destDao.getAllDestinations();
    }
}
