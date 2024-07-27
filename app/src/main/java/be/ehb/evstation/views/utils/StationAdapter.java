package be.ehb.evstation.views.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.evstation.R;
import be.ehb.evstation.model.Station;
import be.ehb.evstation.views.DetailsActivity;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {
    private List<Station> stationList;
    private Context context;

    public StationAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.evstation_item, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.operatorNameTv.setText("Operator: " + station.getOperatorName());
        holder.powerTv.setText("Power: " + station.getPower());
        holder.connectorTypeTv.setText("Connector: " + station.getConnectorType());
        holder.chargingPointsTv.setText("Charging Points: " + station.getChargingPoints());
        holder.municipalityTv.setText("Municipality: " + station.getMunicipality());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("station", station);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public void updateStations(List<Station> newStations) {
        this.stationList = newStations;
        notifyDataSetChanged();
    }

    public static class StationViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView operatorNameTv, chargingPointsTv, connectorTypeTv, powerTv, municipalityTv;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.evStationCardView);
            operatorNameTv = itemView.findViewById(R.id.operatorName);
            chargingPointsTv = itemView.findViewById(R.id.chargingPoints);
            connectorTypeTv = itemView.findViewById(R.id.connectorType);
            powerTv = itemView.findViewById(R.id.power);
            municipalityTv = itemView.findViewById(R.id.municipality);
        }
    }
}
