package com.codecool.tripplanner.destinationedit;

import com.codecool.tripplanner.BasePresenter;
import com.codecool.tripplanner.BaseView;


public interface EditDestinationContract {

    interface View extends BaseView<Presenter>{

        void showSaveError();

        void showSaveSuccess();
    }

    interface Presenter extends BasePresenter{

        void addNewDestination(String destName, String imageUrl, String lat, String longitude);

    }
}
