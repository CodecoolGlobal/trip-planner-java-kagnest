package com.codecool.tripplanner.destinationlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationListActivity extends AppCompatActivity implements DestinationsContract.View {

    private DestinationsContract.Presenter presenter;
    private DestinationListAdapter destinationListAdapter;
    private RecyclerView recyclerView;
    private List<Destination> destinations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new DestinationsPresenter(getApplication(), this);
        presenter.onAttach();
        presenter.loadDestinations();

        recyclerView = findViewById(R.id.dest_recyclerview);
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
    public void showDestinations(List<Destination> destList) {
        destinations.clear();
        destinations.addAll(destList);
        destinationListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
