package myapp.bank.sos.banquetunisie;

import com.google.android.gms.maps.model.LatLng;


public class Application extends android.app.Application {
    private LatLng position;

    public Application() {
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }





}
