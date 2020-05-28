package com.codecool.tripplanner.destinationlist;

import android.app.Application;
import android.os.AsyncTask;

import com.codecool.tripplanner.data.Destination;
import com.codecool.tripplanner.data.DestinationRepository;

import java.util.Arrays;
import java.util.List;


public class DestinationsPresenter implements DestinationsContract.Presenter{

    private DestinationRepository destRepository;
    private DestinationsContract.View destListView;
    private List<Destination> destinationList;

    public DestinationsPresenter(Application application, DestinationsContract.View destListView) {
        this.destRepository = new DestinationRepository(application);
        this.destListView = destListView;
    }

    @Override
    public void loadDestinations() {
        new AsyncDataAquire().execute();
    }

    @Override
    public void onAttach() {
        destListView.setPresenter(this);
    }

    @Override
    public void onDetach() {
        destListView = null;
        destRepository = null;
    }

    private class AsyncDataAquire extends AsyncTask<Void, Integer, List<Destination>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            TODO PROGRESS BAR
        }

        @Override
        protected List<Destination> doInBackground(Void... voids) {
            destinationList = destRepository.getDestinationList();
            return destinationList;
        }

        @Override
        protected void onPostExecute(List<Destination> destinations) {
            super.onPostExecute(destinations);
            destListView.showDestinations(destinationList);
        }
    }
}
