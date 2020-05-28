package com.codecool.tripplanner.destinationlist;

import com.codecool.tripplanner.BasePresenter;
import com.codecool.tripplanner.BaseView;
import com.codecool.tripplanner.data.Destination;

import java.util.List;

public interface DestinationsContract {

    interface View extends BaseView<Presenter>{

        void showDestinations(List<Destination> destinations);
    }

    interface Presenter extends BasePresenter{

        void loadDestinations();
    }

}
