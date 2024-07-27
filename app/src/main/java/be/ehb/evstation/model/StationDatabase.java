package be.ehb.evstation.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import be.ehb.evstation.model.utils.Converters;

@Database(version = 1, entities = {Station.class}, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class StationDatabase extends RoomDatabase {

    private static StationDatabase INSTANCE;

    public static StationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    StationDatabase.class, "stationdatabase.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public abstract StationDAO getStationDAO();
}
