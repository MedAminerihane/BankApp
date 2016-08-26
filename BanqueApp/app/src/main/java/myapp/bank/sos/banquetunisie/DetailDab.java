package myapp.bank.sos.banquetunisie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailDab extends AppCompatActivity {
    @InjectView(R.id.city)
    TextView city;
    @InjectView(R.id.adresse_dab)
    TextView address ;
    @InjectView(R.id.name_dab)
    TextView name;
    @InjectView(R.id.monnaie)
    TextView monnaie;
    @InjectView(R.id.etat)
    TextView etat;
    @InjectView(R.id.distane_dab)
    TextView dist;
    @InjectView(R.id.recalmer)
    Button reclam;


     ATM atm;
    LatLng mypos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dab);
        ButterKnife.inject(this);
        GPSTracker gps;

        ContextThemeWrapper ctw = new ContextThemeWrapper(DetailDab.this, R.style.AppCompatAlertDialogStyle);
        final android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(ctw);
        gps = new GPSTracker(DetailDab.this);

        atm= getIntent().getExtras().getParcelable("atm");
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);


        city.setText(atm.getEnCityName());
        address.setText(atm.getEnAddress());
        name.setText(atm.getEnName());

        if(atm.hasForeignCurrency==1) {
            monnaie.setText("Monnaie étrangère disponible");
        }else
        {
            monnaie.setText("Monnaie étrangère non disponible");
        }



      String whereClause = "idDab ="+String.valueOf(atm.getId());
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause( whereClause );
       // BackendlessCollection<Reclamation> result = Backendless.Persistence.of( Reclamation.class ).find( dataQuery );
      //  Toast.makeText(getApplicationContext(),result.getData().toString(),Toast.LENGTH_LONG).show();*/
        Backendless.Persistence.of( Reclamation.class).find( dataQuery ,new AsyncCallback<BackendlessCollection<Reclamation>>(){


            @Override
            public void handleResponse(BackendlessCollection<Reclamation> response) {
               // Toast.makeText(getApplicationContext(),response.getData().toString(),Toast.LENGTH_LONG).show();
                if (response.getData().size()!=0){
                    etat.setText("En Panne");
                    etat.setTextColor(getResources().getColor(R.color.Red));
                }else {
                    etat.setText("Fonctionnel");
                    //etat.setTextColor(getResources().getColor(R.color.Red));
                }
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                etat.setText("Fonctionnel");
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
        if (gps.canGetLocation()) {

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();
            mypos= new LatLng(lat,lng);

        }else{

            mypos= ((Application) this.getApplication()).getPosition();

        }

        double distance = getDistance(mypos.latitude, mypos.longitude, atm.getLat(), atm.getLon());
        String ds = String.valueOf(Math.round(distance));

        dist.setText(ds + "m");
     //   etat.setText("Fonctionnel");
       LatLng posatm= new LatLng(atm.getLat(), atm.getLon());
        mapFragment.getMap().addMarker(new MarkerOptions()
                .position(posatm)
                .alpha(0.8f)
                .snippet(ds + "m")
                .title(atm.getEnAddress())
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(posatm, 14));


        reclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isOnline()) {
                    alertDialog.setTitle("Reclamation");
                    alertDialog.setMessage("Voulez vous vraiment reclamez une panne ?");
                    AlertDialog.Builder builder = alertDialog.setPositiveButton("Oui",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Backendless.Persistence.save(new Reclamation(atm.getId(), atm.getEnName(), "En panne"), new BackendlessCallback<Reclamation>() {
                                        @Override
                                        public void handleResponse(Reclamation reclamation) {
                                            etat.setText("En Panne");
                                            etat.setTextColor(getResources().getColor(R.color.Red));
                                        }
                                    });


                                }
                            });
                    alertDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    final AlertDialog dialog = alertDialog.create();
                    dialog.show();
                }
            else {

                    alertDialog.setTitle("Connexion indisponible");

                    // Setting Dialog Message
                    alertDialog.setMessage("Veuillez connecter a internet pour reclamer une panne");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.noincon);
                    alertDialog.setNegativeButton("Retour",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // Write your code here to execute after
                                    // dialog closed
                                    dialog.dismiss();

                                }
                            });

                    alertDialog.setPositiveButton("Se connecter",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                                }
                            });

                    final AlertDialog dialog = alertDialog.create();
                    dialog.show();


                }
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

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
