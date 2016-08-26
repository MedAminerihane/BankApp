package myapp.bank.sos.banquetunisie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ReclamationActivity extends AppCompatActivity {
    @InjectView(R.id.send)
    ImageView send;
    @InjectView(R.id.subject)
    EditText subject;
    @InjectView(R.id.dest)
    EditText dest;
    @InjectView(R.id.mail)
    EditText mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        ButterKnife.inject(this);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{String.valueOf(dest.getText())});
                i.putExtra(Intent.EXTRA_SUBJECT , subject.getText());
                i.putExtra(Intent.EXTRA_TEXT   , mail.getText());
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
