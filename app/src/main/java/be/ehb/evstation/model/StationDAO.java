package be.ehb.evstation.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StationDAO {

    @Insert
    void insertStation(Station s);

    @Query("SELECT * FROM Station ORDER BY operatorName")
    LiveData<List<Station>> getAllStations();

    @Query("SELECT * FROM Station WHERE municipality LIKE '%' || :municipality || '%'")
    LiveData<List<Station>> getStationByMunicipality(String municipality);


    @Query("SELECT * FROM Station WHERE gid = :gid")
    Station getStationByGid(String gid);

    @Update
    void updateStation(Station s);

    @Delete
    void deleteStation(Station s);

    @Query("SELECT COUNT(*) FROM Station")
    int getCount();
}
