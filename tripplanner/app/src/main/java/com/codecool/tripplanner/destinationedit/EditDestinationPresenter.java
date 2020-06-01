package com.codecool.tripplanner.destinationedit;

import android.app.Application;

import com.codecool.tripplanner.data.Destination;
import com.codecool.tripplanner.data.DestinationRepository;

import org.apache.commons.lang3.StringUtils;


public class EditDestinationPresenter implements EditDestinationContract.Presenter {

    private DestinationRepository destRepository;
    private EditDestinationContract.View editDestView;

    public EditDestinationPresenter(Application application, EditDestinationContract.View editDestView) {
        this.destRepository = new DestinationRepository(application);
        this.editDestView = editDestView;
    }

    @Override
    public void onAttach() {
        editDestView.setPresenter(this);
    }

    @Override
    public void onDetach() {
        editDestView = null;
    }

    @Override
    public void addNewDestination(String destName, String imageUrl, String lat, String longitude) {
        String destNameC = StringUtils.capitalize(destName);
        Destination destination = new Destination(destNameC, imageUrl, lat, longitude);
        try {
            destRepository.insert(destination);
        } catch (Exception e){
            editDestView.showSaveError();
        }
        editDestView.showSaveSuccess();
    }
}
