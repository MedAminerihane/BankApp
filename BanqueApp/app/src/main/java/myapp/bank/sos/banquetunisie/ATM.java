package myapp.bank.sos.banquetunisie;

import android.os.Parcel;
import android.os.Parcelable;


public class ATM implements Parcelable {

    int id;
    String country;
    String enName;
    String arName;
    String enAddress;
    String arAddress;
    int hasForeignCurrency;
    int onlineDeposit	;
    float lat	;
    float lon;
    String enCityName;
    String arCityName;
    String enDistrictName;
    String arDistrictName;
    String type;

    @Override
    public String toString() {
        return "ATM{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", enName='" + enName + '\'' +
                ", arName='" + arName + '\'' +
                ", enAddress='" + enAddress + '\'' +
                ", arAddress='" + arAddress + '\'' +
                ", hasForeignCurrency=" + hasForeignCurrency +
                ", onlineDeposit=" + onlineDeposit +
                ", lat=" + lat +
                ", lon=" + lon +
                ", enCityName='" + enCityName + '\'' +
                ", arCityName='" + arCityName + '\'' +
                ", enDistrictName='" + enDistrictName + '\'' +
                ", arDistrictName='" + arDistrictName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public ATM(){}
    public ATM(int id, String country, String enName, String arName, String enAddress, String arAddress, int hasForeignCurrency, int onlineDeposit, float lat, float lon, String enCityName, String arCityName, String enDistrictName, String arDistrictName, String type) {
        this.id = id;
        this.country = country;
        this.enName = enName;
        this.arName = arName;
        this.enAddress = enAddress;
        this.arAddress = arAddress;
        this.hasForeignCurrency = hasForeignCurrency;
        this.onlineDeposit = onlineDeposit;
        this.lat = lat;
        this.lon = lon;
        this.enCityName = enCityName;
        this.arCityName = arCityName;
        this.enDistrictName = enDistrictName;
        this.arDistrictName = arDistrictName;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getArName() {
        return arName;
    }

    public void setArName(String arName) {
        this.arName = arName;
    }

    public String getEnAddress() {
        return enAddress;
    }

    public void setEnAddress(String enAddress) {
        this.enAddress = enAddress;
    }

    public String getArAddress() {
        return arAddress;
    }

    public void setArAddress(String arAddress) {
        this.arAddress = arAddress;
    }

    public int getHasForeignCurrency() {
        return hasForeignCurrency;
    }

    public void setHasForeignCurrency(int hasForeignCurrency) {
        this.hasForeignCurrency = hasForeignCurrency;
    }

    public int getOnlineDeposit() {
        return onlineDeposit;
    }

    public void setOnlineDeposit(int onlineDeposit) {
        this.onlineDeposit = onlineDeposit;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getEnCityName() {
        return enCityName;
    }

    public void setEnCityName(String enCityName) {
        this.enCityName = enCityName;
    }

    public String getArCityName() {
        return arCityName;
    }

    public void setArCityName(String arCityName) {
        this.arCityName = arCityName;
    }

    public String getEnDistrictName() {
        return enDistrictName;
    }

    public void setEnDistrictName(String enDistrictName) {
        this.enDistrictName = enDistrictName;
    }

    public String getArDistrictName() {
        return arDistrictName;
    }

    public void setArDistrictName(String arDistrictName) {
        this.arDistrictName = arDistrictName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.country);
        dest.writeString(this.enName);
        dest.writeString(this.arName);
        dest.writeString(this.enAddress);
        dest.writeString(this.arAddress);
        dest.writeInt(this.hasForeignCurrency);
        dest.writeInt(this.onlineDeposit);
        dest.writeFloat(this.lat);
        dest.writeFloat(this.lon);
        dest.writeString(this.enCityName);
        dest.writeString(this.arCityName);
        dest.writeString(this.enDistrictName);
        dest.writeString(this.arDistrictName);
        dest.writeString(this.type);
    }

    private ATM(Parcel in) {
        this.id = in.readInt();
        this.country = in.readString();
        this.enName = in.readString();
        this.arName = in.readString();
        this.enAddress = in.readString();
        this.arAddress = in.readString();
        this.hasForeignCurrency = in.readInt();
        this.onlineDeposit = in.readInt();
        this.lat = in.readFloat();
        this.lon = in.readFloat();
        this.enCityName = in.readString();
        this.arCityName = in.readString();
        this.enDistrictName = in.readString();
        this.arDistrictName = in.readString();
        this.type = in.readString();
    }

    public static final Creator<ATM> CREATOR = new Creator<ATM>() {
        public ATM createFromParcel(Parcel source) {
            return new ATM(source);
        }

        public ATM[] newArray(int size) {
            return new ATM[size];
        }
    };
}
