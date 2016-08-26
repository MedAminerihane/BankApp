package myapp.bank.sos.banquetunisie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimulatorActivity extends AppCompatActivity {

String a,b,c;
    @InjectView(R.id.seekBarMontant)
    SeekBar seekMontant;
    @InjectView(R.id.seekBarDuree)
    SeekBar seekDuree;
    @InjectView(R.id.montant)
    TextView montant;
    @InjectView(R.id.mois)
    TextView mois;
    @InjectView(R.id.apayer)
    TextView apayerpermonth;
    @InjectView(R.id.apayerTotale)
    TextView apayerall;


    @InjectView(R.id.calcul)
    Button calcul;
    @InjectView(R.id.save)
    Button save;
    @InjectView(R.id.setting)
    Button setting;
    @InjectView(R.id.tmm)
    EditText tmm;
    @InjectView(R.id.interet)
    EditText interet;
float tmmvalue, interetvalue;
    private static final String PREF_NAME = "RegPref";
    static SharedPreferences pref;

    static SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devise);

        ButterKnife.inject(this);
        pref = getApplication().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        tmmvalue = pref.getFloat("tmm", 4.22f);
        interetvalue= pref.getFloat("interet", 5f);

        tmm.setText(String.valueOf(tmmvalue));
        interet.setText(String.valueOf(interetvalue));
        seekDuree.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mois.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekMontant.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                montant.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
tmm.setEnabled(true);
                interet.setEnabled(true);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmmvalue=Float.valueOf(tmm.getText().toString());
editor.putFloat("tmm",tmmvalue);
                interetvalue=Float.valueOf(interet.getText().toString());
                editor.putFloat("interet",interetvalue);
                editor.commit();
                tmm.setEnabled(false);
                interet.setEnabled(false);

            }
        });
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a =String.valueOf(Integer.valueOf(montant.getText().toString())+Integer.valueOf(montant.getText().toString())*((tmmvalue+interetvalue)/100));
                apayerall.setText(a);
                apayerpermonth.setText(String.valueOf(Float.valueOf(a)/Integer.valueOf(mois.getText().toString())));



            }
        });




    }




}
