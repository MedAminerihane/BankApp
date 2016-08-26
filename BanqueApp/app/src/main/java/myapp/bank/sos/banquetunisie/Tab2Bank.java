package myapp.bank.sos.banquetunisie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class Tab2Bank extends Fragment {


    TextView city,address,dist;
    LatLng pos;
   static SupportMapFragment mapFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2bank,container,false);

    city=(TextView)v.findViewById(R.id.city);
        address=(TextView)v.findViewById(R.id.adresse_dab);
        dist=(TextView)v.findViewById(R.id.distane_dab);

        mapFragment  = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        pos=((Application) getActivity().getApplication()).getPosition();
        mapFragment.getMap().addMarker(new MarkerOptions()
                .position(pos)
                .alpha(0.8f)
                .snippet( "position actuelle")
                .title("Votre position")
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mapFragment.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
        Circle circle = mapFragment.getMap().addCircle(new CircleOptions().center(pos)
                .radius(50).strokeColor(0x40ff0000)
                .fillColor(0x40ff0000));

        return v;


    }
    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {


    }

















}
