package myapp.bank.sos.banquetunisie;


import android.os.Parcel;
import android.os.Parcelable;


public class Bank implements Parcelable {
     int id ;
     String country;
    String enName;
    String arname;
    String enadresse;
    String aradresse;
    String workinghours;
    double lat;
    double lon;
    String phone;
    String encityname;
    String elitecentre;
    String corporateservice;
    String type;



    public Bank(){}

    public Bank(int id, String country, String enName, String arname, String enadresse, String aradresse, String workinghours, double lat, double lon, String phone, String encityname, String elitecentre, String corporateservice, String type) {
        this.id = id;
        this.country = country;
        this.enName = enName;
        this.arname = arname;
        this.enadresse = enadresse;
        this.aradresse = aradresse;
        this.workinghours = workinghours;
        this.lat = lat;
        this.lon = lon;
        this.phone = phone;
        this.encityname = encityname;
        this.elitecentre = elitecentre;
        this.corporateservice = corporateservice;
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

    public String getArname() {
        return arname;
    }

    public void setArname(String arname) {
        this.arname = arname;
    }

    public String getEnadresse() {
        return enadresse;
    }

    public void setEnadresse(String enadresse) {
        this.enadresse = enadresse;
    }

    public String getAradresse() {
        return aradresse;
    }

    public void setAradresse(String aradresse) {
        this.aradresse = aradresse;
    }

    public String getWorkinghours() {
        return workinghours;
    }

    public void setWorkinghours(String workinghours) {
        this.workinghours = workinghours;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEncityname() {
        return encityname;
    }

    public void setEncityname(String encityname) {
        this.encityname = encityname;
    }

    public String getElitecentre() {
        return elitecentre;
    }

    public void setElitecentre(String elitecentre) {
        this.elitecentre = elitecentre;
    }

    public String getCorporateservice() {
        return corporateservice;
    }

    public void setCorporateservice(String corporateservice) {
        this.corporateservice = corporateservice;
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
        dest.writeString(this.arname);
        dest.writeString(this.enadresse);
        dest.writeString(this.aradresse);
        dest.writeString(this.workinghours);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
        dest.writeString(this.phone);
        dest.writeString(this.encityname);
        dest.writeString(this.elitecentre);
        dest.writeString(this.corporateservice);
        dest.writeString(this.type);
    }

    private Bank(Parcel in) {
        this.id = in.readInt();
        this.country = in.readString();
        this.enName = in.readString();
        this.arname = in.readString();
        this.enadresse = in.readString();
        this.aradresse = in.readString();
        this.workinghours = in.readString();
        this.lat = in.readDouble();
        this.lon = in.readDouble();
        this.phone = in.readString();
        this.encityname = in.readString();
        this.elitecentre = in.readString();
        this.corporateservice = in.readString();
        this.type = in.readString();
    }

    public static final Creator<Bank> CREATOR = new Creator<Bank>() {
        public Bank createFromParcel(Parcel source) {
            return new Bank(source);
        }

        public Bank[] newArray(int size) {
            return new Bank[size];
        }
    };

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", enName='" + enName + '\'' +
                ", arname='" + arname + '\'' +
                ", enadresse='" + enadresse + '\'' +
                ", aradresse='" + aradresse + '\'' +
                ", workinghours='" + workinghours + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", phone='" + phone + '\'' +
                ", encityname='" + encityname + '\'' +
                ", elitecentre='" + elitecentre + '\'' +
                ", corporateservice='" + corporateservice + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}