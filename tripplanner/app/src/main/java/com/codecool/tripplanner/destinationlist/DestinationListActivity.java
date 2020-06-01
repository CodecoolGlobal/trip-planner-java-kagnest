package com.codecool.tripplanner.destinationlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;
import com.codecool.tripplanner.destinationedit.AddDestinationActivity;
import com.codecool.tripplanner.destinationmap.DestinationMapActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DestinationListActivity extends AppCompatActivity implements DestinationsContract.View {

    private static final int ADD_DEST = 1;
    private DestinationsContract.Presenter presenter;
    private DestinationListAdapter destinationListAdapter;
    private List<Destination> destinations = new ArrayList<>();
    @BindView(R.id.dest_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.progress_view)
    FrameLayout progressLayout;
    @BindView(R.id.add_dest)
    FloatingActionButton fab;
    @BindView(R.id.empty_list)
    TextView emptyListText;
    @BindView(R.id.error_msg)
    TextView errorMsg;

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
                Destination dest = destinations.get(position);
                Intent intent = new Intent(DestinationListActivity.this, DestinationMapActivity.class);
                intent.putExtra("name", dest.getDestinationName());
                intent.putExtra("lat",dest.getLat() );
                intent.putExtra("long", dest.getLongitude());
                startActivity(intent);
            }
        });

        int column = getResources().getInteger(R.integer.grid_column_number);
        recyclerView.setAdapter(destinationListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, column));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DestinationListActivity.this, AddDestinationActivity.class);
                startActivityForResult(intent, ADD_DEST);
            }
        });
    }


    @Override
    public void setPresenter(DestinationsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoadingScreen() {
        progressLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.GONE);
    }

    @Override
    public void showDestinations(List<Destination> destList) {
        destinations.clear();
        destinations.addAll(destList);
        destinationListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyList() {
        emptyListText.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeLoadingScreen() {
        progressLayout.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        errorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_DEST){
            if (resultCode == RESULT_OK){
                presenter.loadDestinations();
            }
        }
    }
}
