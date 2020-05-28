package com.codecool.tripplanner.data;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Destination.class}, version = 1, exportSchema = false)
public abstract class TripPlannerDatabase extends RoomDatabase {

    public abstract DestinationDao destinationDao();

    private static volatile TripPlannerDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static TripPlannerDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (TripPlannerDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TripPlannerDatabase.class, "trip_planner_db"  )
                            .addCallback(initData)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback initData = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(()->{
                DestinationDao dao = INSTANCE.destinationDao();
                dao.deleteAll();

                Destination dest = new Destination("Paris", "https://img5.goodfon.com/wallpaper/nbig/7/81/eifeleva-bashnia-leto-parizh.jpg",  "geo:48.8566, 2.3522");
                dao.insert(dest);

            });
        }
    };
}
