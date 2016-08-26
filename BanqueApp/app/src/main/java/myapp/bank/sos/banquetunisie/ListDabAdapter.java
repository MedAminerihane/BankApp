package myapp.bank.sos.banquetunisie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;


public class ListDabAdapter extends ArrayAdapter<ATM> {


    Context context;
    private int resourceId = 0;
    private LayoutInflater inflater;



    public ListDabAdapter(Context context, int resourceId, List<ATM> atms) {
        super(context, 0, atms);

        this.resourceId = resourceId;
        this.context = context;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PlaceHolder holder = new PlaceHolder();

        if (row == null) {
            row = inflater.inflate(resourceId, parent, false);

            holder.name = (TextView) row.findViewById(R.id.name);

            holder.adresse = (TextView) row.findViewById(R.id.adresse);
            holder.dist=(TextView)row.findViewById(R.id.distance);

            row.setTag(holder);
        } else {
            holder = (PlaceHolder) row.getTag();
        }

        ATM atm = getItem(position);
        holder.name.setText(atm.getEnName());

        holder.adresse.setText(atm.getEnAddress());
    //    holder.dist.setText(String.valueOf(place.getLat()));


      LatLng pos = ((Application) context.getApplicationContext()).getPosition();
        double distance = getDistance(pos.latitude, pos.longitude, atm.getLat(), atm.getLon());
        String ds = String.valueOf(Math.round(distance));
        holder.dist.setText(ds+"m");


        return row;
    }

    public static class PlaceHolder {

        TextView name;

        TextView adresse;
        TextView dist;

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