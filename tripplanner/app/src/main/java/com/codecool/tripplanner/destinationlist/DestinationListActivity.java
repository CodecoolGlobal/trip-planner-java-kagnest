package com.codecool.tripplanner.destinationlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationListActivity extends AppCompatActivity implements DestinationsContract.View {

    private DestinationsContract.Presenter presenter;
    private DestinationListAdapter destinationListAdapter;
    private List<Destination> destinations = new ArrayList<>();
    @BindView(R.id.dest_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.progress_view)
    FrameLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new DestinationsPresenter(getApplication(), this);
        presenter.onAttach();
        presenter.loadDestinations();

        destinationListAdapter = new DestinationListAdapter(destinations, new DestinationListAdapter.DestAdapterListener() {

            @Override
            public void displayDestinationOnMap(View view, int position) {

//      TODO google maps
            }
        });
        recyclerView.setAdapter(destinationListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void setPresenter(DestinationsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoadingScreen() {
        progressLayout.setVisibility(View.VISIBLE);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDestinations(List<Destination> destList) {
        destinations.clear();
        destinations.addAll(destList);
        destinationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void removeLoadingScreen() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }


}
