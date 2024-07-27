package be.ehb.evstation.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("records")
    Call<ApiResponse> getStations();
}
