package myapp.bank.sos.banquetunisie;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DabActivity extends AppCompatActivity {
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    int Numboftabs =2;
    static LatLng pos;
    List<ATM> listatm;
   public ListView listView;
   public static   ListDabAdapter listDabAdapter;
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dab);

        ButterKnife.inject(this);

        GPSTracker gps;

        gps = new GPSTracker(DabActivity.this);

        CharSequence Titles[]=new String[]{getResources().getString(R.string.tab1Dab),getResources().getString(R.string.tab2Dab)};
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);
        MapsInitializer.initialize(getApplicationContext());
        fragmentManager = getSupportFragmentManager();

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);

        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);





        if (gps.canGetLocation()) {

            double lat = gps.getLatitude();
            double lng = gps.getLongitude();
            pos= new LatLng(lat,lng);
            ((Application) this.getApplication()).setPosition(pos);
        }else{

            pos = ((Application) this.getApplication()).getPosition();

        }

        ATMData atm = new ATMData();
        atm.execute();



    }




    private class ATMData extends AsyncTask<String, Void, List<ATM>> {

        @Override
        protected List<ATM> doInBackground(String... params) {
            listatm=new ArrayList<ATM>();

            try {

                AssetManager am=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
                InputStream is=am.open("Datafile.xls");
                Workbook wb =Workbook.getWorkbook(is);
                Sheet s=wb.getSheet(1);
                int row =s.getRows();
                Log.v("ee",""+row);

                int col=s.getColumns();
                Log.v("ee",""+col);




                // String xx="";
                for(int i=1;i<row;i++)
                {
                    ATM atm = new ATM();

                    for (int c=0;c<15;c++)
                    {
                        Cell z=s.getCell(c,i);



                        if(c==0){ atm.setId(Integer.valueOf(z.getContents()));

                        }
                        if(c==1){atm.setCountry(z.getContents());

                        }
                        if (c==2)  {atm.setEnName(z.getContents());

                        }
                        if (c==3) {atm.setArName(z.getContents());}
                        if (c==4)  {atm.setEnAddress(z.getContents());}
                        if (c==5)  {atm.setArAddress(z.getContents());}
                        if (c==6)  {atm.setHasForeignCurrency(Integer.valueOf(z.getContents()));}
                        if (c==7) { atm.setOnlineDeposit(Integer.valueOf(z.getContents()));}
                        if (c==8) { atm.setLat(Float.valueOf(z.getContents()));}
                        if (c==9)  {atm.setLon(Float.valueOf(z.getContents()));}
                        if (c==10) {atm.setEnCityName(z.getContents());}
                        if (c==11)  {atm.setArCityName(z.getContents());}
                        if (c==12)  {atm.setEnDistrictName(z.getContents());}
                        if (c==13)  {atm.setArDistrictName(z.getContents());}
                        if (c==14)  {atm.setType(z.getContents());}




                    }

                    listatm.add(atm);
                    //Toast.makeText(getApplicationContext(),xx,Toast.LENGTH_LONG).show();
                }
            }



            catch (Exception e)
            {
                Log.v("ee",""+e);
            }

//            Toast.makeText(getActivity(),listatm.get(8).getArCityName().toString(),Toast.LENGTH_LONG).show();
            return listatm;

        }


        @Override
        protected void onPostExecute(List<ATM> atms) {
            super.onPostExecute(atms);

          /*  for(Iterator<ATM> i = atms.iterator(); i.hasNext(); ) {
                Toast.makeText(getActivity(),i.toString(),Toast.LENGTH_LONG).show();
            }
*/

          //  Toast.makeText(getApplicationContext() ,atms.get(3).toString(),Toast.LENGTH_LONG).show();
           listDabAdapter = new ListDabAdapter(getApplicationContext(),
                    R.layout.one_item, atms);

            Tab1Dab.listView.setAdapter(listDabAdapter);


            for(int i=0;i<atms.size();i++) {
                double lat =atms.get(i).getLat();
                double ln =atms.get(i).getLon();
               LatLng p = new LatLng(lat,ln);
                LatLng pos = ((Application)getApplicationContext()).getPosition();
                double distance = getDistance(pos.latitude, pos.longitude,lat, ln);
                String ds = String.valueOf(Math.round(distance));
                Tab2Dab.mapFragment.getMap().addMarker(new MarkerOptions()
                        .position(p)
                        .alpha(0.8f)
                        .snippet(ds+ " mètres")
                        .title(atms.get(i).enAddress)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.atmmarker)));

            }
        }

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
