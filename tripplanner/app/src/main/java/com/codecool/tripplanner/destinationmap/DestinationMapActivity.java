package com.codecool.tripplanner.destinationmap;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.TextView;

import com.codecool.tripplanner.R;
import com.codecool.tripplanner.data.Destination;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DestinationMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.dest_name_map)
    TextView nameText;
    @BindView(R.id.gps_text)
    TextView gpsText;


    private String destName;
    private String lat;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_map);

        ButterKnife.bind(this);

        destName = getIntent().getStringExtra("name");
        lat = getIntent().getStringExtra("lat");
        longitude = getIntent().getStringExtra("long");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String nameLabel = "Destination: " + destName;
        String coordsLabel = "Gps Coordinates: " + lat + ", " + longitude;
        nameText.setText(nameLabel);
        gpsText.setText(coordsLabel);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng dest = new LatLng(Double.parseDouble(lat), Double.parseDouble(longitude));
        googleMap.addMarker(new MarkerOptions().position(dest).title(destName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest));
    }
}