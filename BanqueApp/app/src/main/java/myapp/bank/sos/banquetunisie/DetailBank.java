package myapp.bank.sos.banquetunisie;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailBank extends AppCompatActivity {
    @InjectView(R.id.city)
    TextView city;
    @InjectView(R.id.adresse_bank)
    TextView address;
    @InjectView(R.id.name_bank)
    TextView name;
    @InjectView(R.id.hour)
    TextView hour;
    @InjectView(R.id.service)
    TextView service;
    @InjectView(R.id.distane_bank)
    TextView dist;
    @InjectView(R.id.Phone)
    TextView phone;
    @InjectView(R.id.call)
    FloatingActionButton call;

    LatLng mypos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bank);
        ButterKnife.inject(this);
        GPSTracker gps;

        gps = new GPSTracker(DetailBank.this);

        Bank bank = getIntent().getExtras().getParcelable("bank");


        // Toast.makeText(getApplicationContext(),bank.toString(),Toast.LENGTH_LONG).show();
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        city.setText(bank.getEncityname());
        address.setText(bank.getEnadresse());
        hour.setText("Heure de travail:  "+ bank.getWorkinghours());
        name.setText(bank.getEnName());
        service.setText("Services d'entreprise:  " + bank.getCorporateservice());
        if(bank.getCorporateservice().equalsIgnoreCase("yes")){
            service.setTextColor(getResources().getColor(R.color.Green));

        }else
        {
            service.setTextColor(getResources().getColor(R.color.Green));
        }

        phone.setText(bank.getPhone());

        // Toast.makeText(getApplicationContext(),"city:" + bank.getEncityname()+" adress : " + bank.getEnadresse() + "service :" + bank.getCorporateservice(),Toast.LENGTH_LONG).show();

        if (gps.canGetLocation()) {

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();
            mypos = new LatLng(lat, lng);

        } else {

            mypos = ((Application) this.getApplication()).getPosition();

        }

        double distance = getDistance(mypos.latitude, mypos.longitude, bank.getLat(), bank.getLon());
        String ds = String.valueOf(Math.round(distance));

        dist.setText(ds + "m");

        LatLng posatm = new LatLng(bank.getLat(), bank.getLon());
        mapFragment.getMap().addMarker(new MarkerOptions()
                .position(posatm)
                .alpha(0.8f)
                .snippet(ds + "m")
                .title(bank.getEnadresse())
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(posatm, 14));

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });

    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        //code for Distance in Kilo Meter
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.abs(Math.round(rad2deg(Math.acos(dist)) * 60 * 1.1515 * 1.609344 * 1000));
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad / Math.PI * 180.0);
    }
}
