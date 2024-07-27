package be.ehb.evstation.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import be.ehb.evstation.R;
import be.ehb.evstation.model.Station;

public class DetailsActivity extends AppCompatActivity {
    private TextView chargingPointsTv, operatorNameTV, connectorTypeTV, powerTv, chargingPointsTV, postcodeTV, municipalityTV;
    private Station station;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        station = getIntent().getParcelableExtra("station");
        initializeVariables();
        if (station != null) {
            setDataToWidgets();
            addMapFragment();
        }

        btn = findViewById(R.id.back_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();  // Go back to the previous activity
            }
        });
    }

    private void setDataToWidgets() {

        chargingPointsTv.setText("Charging Points" + station.getChargingPoints());
        operatorNameTV.setText("Operator Name: " + station.getOperatorName());
        connectorTypeTV.setText("Connector Type: " + station.getConnectorType());
        postcodeTV.setText("Postcode: " + station.getPostcode());
        powerTv.setText("Power: " + station.getPower() + " Wat");
        chargingPointsTV.setText("Charging Points: " + station.getChargingPoints());
        municipalityTV.setText("Municipality: " + station.getMunicipality());
    }

    private void initializeVariables() {
        chargingPointsTv = findViewById(R.id.detailActivityChargingPoints);
        operatorNameTV = findViewById(R.id.detailActivityOperatorName);
        connectorTypeTV = findViewById(R.id.detailActivityConnectorType);
        postcodeTV = findViewById(R.id.detailActivityPostcode);
        powerTv = findViewById(R.id.detailActivityPower);
        chargingPointsTV = findViewById(R.id.detailActivityChargingPoints);
        municipalityTV = findViewById(R.id.detailActivityMunicipality);
    }

    private void addMapFragment() {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble("stationLat", station.getGeoPoint().getLat());
        bundle.putDouble("stationLon", station.getGeoPoint().getLon());
        mapFragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_container, mapFragment);
        fragmentTransaction.commit();
    }
}
