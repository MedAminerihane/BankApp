package myapp.bank.sos.banquetunisie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.backendless.Backendless;

import com.google.android.gms.maps.model.LatLng;
import myapp.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Accueil extends AppCompatActivity {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root)
    FrameLayout root;
    @InjectView(R.id.content_hamburger)
    View contentHamburger;
    @InjectView(R.id.btn_dab)
    Button btn_dab;
    @InjectView(R.id.btn_agence)
    Button btn_agence;
    @InjectView(R.id.btn_devise)
    Button btn_devise;
    @InjectView(R.id.btn_reclamtion)
    Button btn_reclamation;
    @InjectView(R.id.simule)
    Button simulation;
    LatLng pos;
    GPSTracker gps;
//seifeddine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        String appVersion = "v1";
        Backendless.initApp( this, "B95DB472-ED7C-79DD-FF03-F782ECB1EC00", "79432441-DD12-8C6E-FF9C-9FEDF83C4F00", appVersion );
        ButterKnife.inject(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }
        if(((Application) getApplicationContext()).getPosition()==null) {
            ((Application) getApplicationContext()).setPosition(new LatLng(36.800220, 10.186227));
        }
        ContextThemeWrapper ctw = new ContextThemeWrapper(Accueil.this, R.style.AppCompatAlertDialogStyle);
        final android.support.v7.app.AlertDialog.Builder alertDialog = new android.support.v7.app.AlertDialog.Builder(ctw);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        gps = new GPSTracker(Accueil.this);


        if (gps.canGetLocation()) {

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();
            pos= new LatLng(lat,lng);
            ((Application) this.getApplication()).setPosition(pos);

        }else{

            // pos= new LatLng(36.800220, 10.186227);

            alertDialog.setTitle("GPS");

            // Setting Dialog Message
            alertDialog.setMessage("Veuillez activer le service Gps");

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

            alertDialog.setPositiveButton("Paramètre de localisation",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {

                         ((Application) getApplicationContext()).setPosition(new LatLng(36.800173,10.186280));
                       startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                        }
                    });

            final AlertDialog dialog = alertDialog.create();
            dialog.show();




        }

        root.addView(guillotineMenu);
        final GuillotineAnimation gui=   new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(250)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        guillotineMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btn_dab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),DabActivity.class);
                startActivity(i);
            }
        });

        btn_agence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),BankActivity.class);
                startActivity(i);
            }
        });
        btn_reclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ReclamationActivity.class);
                startActivity(i);

            }
        });
btn_devise.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),CurrencyActivity.class);
        startActivity(i);

    }
});


        simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SimulatorActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        gps = new GPSTracker(Accueil.this);
        if (gps.canGetLocation()) {

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();
            pos= new LatLng(lat,lng);
            ((Application) getApplicationContext()).setPosition(pos);
        }
        // Get the Camera instance as the activity achieves full user focus

    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
