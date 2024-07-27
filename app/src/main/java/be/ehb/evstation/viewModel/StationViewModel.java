package be.ehb.evstation.viewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ehb.evstation.api.ApiResponse;
import be.ehb.evstation.api.ApiService;
import be.ehb.evstation.model.Station;
import be.ehb.evstation.model.StationDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationViewModel extends AndroidViewModel {

    private static final String TAG = "StationViewModel";
    private static final String BASE_URL = "https://opendata.brussel.be/api/explore/v2.1/catalog/datasets/bornes-de-recharge-pour-voitures-electriques-rbc/";

    private final ExecutorService mExecutorService = Executors.newFixedThreadPool(2);
    private final StationDatabase mDatabase;
    private final MutableLiveData<ArrayList<Station>> stations;
    private final LiveData<List<Station>> getAllStations;
    private final MutableLiveData<List<Station>> filteredStations = new MutableLiveData<>();

    public StationViewModel(@NonNull Application application) {
        super(application);
        mDatabase = StationDatabase.getInstance(application);
        getAllStations = mDatabase.getStationDAO().getAllStations();
        stations = new MutableLiveData<>();

    }

    public LiveData<List<Station>> getAllStations() {
        return getAllStations;
    }

    public LiveData<List<Station>> getFilteredStations() {
        return filteredStations;
    }

    public void fetchStationsFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<ApiResponse> call = apiService.getStations();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getResults() != null) {
                        List<Station> stationList = apiResponse.getResults();
                        mExecutorService.execute(() -> {
                            for (Station station : stationList) {
                                // To prevent duplication when running the program and fetching the API
                                if (mDatabase.getStationDAO().getStationByGid(station.getGid()) == null) {
                                    mDatabase.getStationDAO().insertStation(station);
                                }
                            }
                            Log.d(TAG, "Inserted new stations into database");
                        });
                    } else {
                        Log.e(TAG, "No data found in API response");
                        Toast.makeText(getApplication(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "API response is not successful");
                    Toast.makeText(getApplication(), "API response is not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "API call failed: " + t.getMessage());
                Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void filterStationsByMunicipality(String municipality) {
        LiveData<List<Station>> stationList = mDatabase.getStationDAO().getStationByMunicipality("%" + municipality + "%");
        stationList.observeForever(new Observer<List<Station>>() {
            @Override
            public void onChanged(List<Station> stations) {
                filteredStations.setValue(stations);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mExecutorService.shutdown();
    }
}
