package com.codecool.tripplanner.destinationlist;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.codecool.tripplanner.data.Destination;
import com.codecool.tripplanner.data.DestinationRepository;

import java.util.Arrays;
import java.util.List;


public class DestinationsPresenter implements DestinationsContract.Presenter {

    private DestinationRepository destRepository;
    private DestinationsContract.View destListView;
    private List<Destination> destinationList;

    public DestinationsPresenter(Application application, DestinationsContract.View destListView) {
        this.destRepository = new DestinationRepository(application);
        this.destListView = destListView;
    }

    @Override
    public void loadDestinations() {
        new AsyncDataAquire(destinationList, destListView, destRepository).execute();
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

    private static class AsyncDataAquire extends AsyncTask<Void, Integer, List<Destination>> {

        private List<Destination> destinationList;
        private DestinationsContract.View view;
        private DestinationRepository destRepository;

        public AsyncDataAquire(List<Destination> destinationList, DestinationsContract.View view, DestinationRepository destRepo) {
            this.destinationList = destinationList;
            this.view = view;
            this.destRepository = destRepo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.showLoadingScreen();
        }

        @Override
        protected List<Destination> doInBackground(Void... voids) {
            try {
                destinationList = destRepository.getDestinationList();
            } catch (Exception e) {
                view.showErrorMessage();
                Log.e("DB_ERROR", Arrays.toString(e.getStackTrace()));
            }
            return destinationList;
        }

        @Override
        protected void onPostExecute(List<Destination> destinations) {
            super.onPostExecute(destinations);
            if (destinationList.size() == 0){
                view.showEmptyList();
            } else {
                view.showDestinations(destinationList);
            }
            view.removeLoadingScreen();
        }
    }
}
