package be.ehb.evstation.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import be.ehb.evstation.model.Station;

public class ApiResponse {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("results")
    private List<Station> results;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Station> getResults() {
        return results;
    }

    public void setResults(List<Station> results) {
        this.results = results;
    }
}
