package be.ehb.evstation.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(indices = {@Index(value = {"gid"}, unique = true)})
public class Station implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("gid")
    private String gid;
    @SerializedName("location_type")
    private String locationType;
    @SerializedName("operator_name")
    private String operatorName;
    @SerializedName("connector_type")
    private String connectorType;
    @SerializedName("power")
    private String power;
    @SerializedName("charging_points")
    private int chargingPoints;
    @SerializedName("postal_code")
    private String postcode;
    @SerializedName("gemeente")
    private String municipality;

    @SerializedName("geo_point_2d")
    private GeoPoint geoPoint;

    public Station() {}

    @Ignore
    public Station(String gid, String locationType, String operatorName, String connectorType, String power, int chargingPoints, String postcode, String municipality) {
        this.gid = gid;
        this.locationType = locationType;
        this.operatorName = operatorName;
        this.connectorType = connectorType;
        this.power = power;
        this.chargingPoints = chargingPoints;
        this.postcode = postcode;
        this.municipality = municipality;
    }

    protected Station(Parcel in) {
        id = in.readLong();
        gid = in.readString();
        locationType = in.readString();
        operatorName = in.readString();
        connectorType = in.readString();
        power = in.readString();
        chargingPoints = in.readInt();
        postcode = in.readString();
        municipality = in.readString();
        geoPoint = in.readParcelable(GeoPoint.class.getClassLoader());
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(gid);
        dest.writeString(locationType);
        dest.writeString(operatorName);
        dest.writeString(connectorType);
        dest.writeString(power);
        dest.writeInt(chargingPoints);
        dest.writeString(postcode);
        dest.writeString(municipality);
        dest.writeParcelable((Parcelable) geoPoint, flags);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getChargingPoints() {
        return chargingPoints;
    }

    public void setChargingPoints(int chargingPoints) {
        this.chargingPoints = chargingPoints;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }



}
