package myapp.bank.sos.banquetunisie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.dd.*;



public class CurrencyActivity extends AppCompatActivity {
    EditText txt;
    Spinner x, y;
    Button bt;
    TextView txtresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ContextThemeWrapper ctw = new ContextThemeWrapper(CurrencyActivity.this, R.style.AppCompatAlertDialogStyle);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctw);
        final CircularProgressButton bt = (CircularProgressButton) findViewById(R.id.button5);
        bt.setIndeterminateProgressMode(true);
        bt.setProgress(0);


        txtresult = (TextView) findViewById(R.id.textresult);
        x = (Spinner) findViewById(R.id.spinner);
        y = (Spinner) findViewById(R.id.spinner2);


        txt = (EditText) findViewById(R.id.textv);


        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("USD");
        spinnerArray.add("TND");
        spinnerArray.add("EUR");
        spinnerArray.add("GBP");
        spinnerArray.add("LYD");
        spinnerArray.add("CAD");
        spinnerArray.add("INR");
        spinnerArray.add("MKD");
        spinnerArray.add("DZD");
        spinnerArray.add("EGP");
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        x.setAdapter(spinnerArrayAdapter);


        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        y.setAdapter(spinnerArrayAdapter);

        bt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

if(isOnline()){
    for (int i = 1; i < 70; i++) {
        bt.setProgress(i);

    }
    if(!txt.getText().toString().equals("")) {
        Double amount = Double.valueOf(txt.getText().toString());
        String x1 = x.getSelectedItem().toString();
        String x2 = y.getSelectedItem().toString();

        String reader = null;
        try {
            reader = getdata(x1, x2);
            JSONObject values = new JSONObject(reader);
            JSONObject xx = values.getJSONObject("query").getJSONObject("results").getJSONObject("rate");
            String valuee = xx.getString("Rate");
            bt.setProgress(100);
            txtresult.setText(String.valueOf(Double.valueOf(valuee) * amount));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    bt.setProgress(0);


            } else {

    alertDialog.setTitle("Connexion indisponible");

    // Setting Dialog Message
    alertDialog.setMessage("Veuillez connecter à internet pour accéder a ce service");

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



    public String getdata(String first, String seconde) throws IOException {



        //  String Data1 = "from-type="+first+"&from-value="+value+"&to-type="+seconde;

        URL myURL = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22"+first+seconde+"%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
        HttpURLConnection myURLConnection = (HttpURLConnection)myURL.openConnection();


        try {


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();

            return stringBuilder.toString();


        }

        catch(IOException e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        finally
        {
            myURLConnection.disconnect();

        }

    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}


