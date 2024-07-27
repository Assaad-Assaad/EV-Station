package be.ehb.evstation.model.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import be.ehb.evstation.model.GeoPoint;

public class Converters {

    @TypeConverter
    public static String fromGeoPoint(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(geoPoint);
    }

    @TypeConverter
    public static GeoPoint toGeoPoint(String geoPointString) {
        if (geoPointString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<GeoPoint>() {}.getType();
        return gson.fromJson(geoPointString, type);
    }
}
