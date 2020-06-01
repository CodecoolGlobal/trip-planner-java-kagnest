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
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TripPlannerDatabase.class, "planner_db"  )
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

                Destination dest = new Destination("Paris", "https://img5.goodfon.com/wallpaper/nbig/7/81/eifeleva-bashnia-leto-parizh.jpg",  "48.8566", "2.3522");
                dao.insert(dest);
                Destination dest2 = new Destination("London", "https://cdn.londonandpartners.com/visit/general-london/areas/river/76709-640x360-houses-of-parliament-and-london-eye-on-thames-from-above-640.jpg",  "51.5074", "0.1278");
                dao.insert(dest2);
                Destination dest3 = new Destination("Osaka", "https://www.jrailpass.com/blog/wp-content/uploads/2019/11/osaka-jo-castle-1280x720.jpg", "34.6937", "135.5023 ");
                dao.insert(dest3);
                Destination dest4 = new Destination("San Francisco", "https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/09/16/15/san-francisco.jpg?w968h681", "37.7749", "122.4197");
                dao.insert(dest4);
            });
        }
    };
}
