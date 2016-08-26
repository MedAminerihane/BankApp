package myapp.bank.sos.banquetunisie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;



public class Tab1Dab extends Fragment {

    List<Bank> listb;
    List<ATM> listatm;
    static ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab1,container,false);



        listView = (ListView) v.findViewById(R.id.list_dab);

        return v;


    }
    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
      //  listView.setAdapter(DabActivity.listDabAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id){

                ATM atm = ((ATM) listView.getItemAtPosition(position));

                Intent i = new Intent(getContext(), DetailDab.class);

                i.putExtra("atm", atm);

                startActivity(i);

            }
        });
    }
}

 /*   private class BankData extends AsyncTask<String, Void, List<Bank>> {

        @Override
        protected List<Bank> doInBackground(String... params) {
            listb=new ArrayList<Bank>();

            try {

                AssetManager am= getActivity().getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
                InputStream is=am.open("Datafile.xls");
                Workbook wb =Workbook.getWorkbook(is);
                Sheet s=wb.getSheet(0);
                int row =s.getRows();
                Log.v("ee",""+row);

                int col=s.getColumns();
                Log.v("ee",""+col);




                // String xx="";
                for(int i=1;i<row;i++)
                {
                    Bank bn = new Bank();

                    for (int c=0;c<col;c++)
                    {
                        Cell z=s.getCell(c,i);



                        if(c==0){ bn.setId(Integer.valueOf(z.getContents()));

                        }
                        if(c==1){bn.setCountry(z.getContents());

                        }
                        if (c==2)  {bn.setEnName(z.getContents());

                        }
                        if (c==3) {bn.setArname(z.getContents());}
                        if (c==4)  {bn.setEnadresse(z.getContents());}
                        if (c==5)  {bn.setAradresse(z.getContents());}
                        if (c==6)  {bn.setWorkinghours(z.getContents());}
                        if (c==7) { bn.setLat(Float.valueOf(z.getContents()));}
                        if (c==8)  {bn.setLon(Float.valueOf(z.getContents()));}
                        if (c==9) {bn.setPhone(z.getContents());}
                        if (c==10)  {bn.setEncityname(z.getContents());}
                        if (c==11)  {bn.setElitecentre(z.getContents());}
                        if (c==12)  {bn.setCorporateservice(z.getContents());}
                        if (c==13)  {bn.setType(z.getContents());}




                    }

                    listb.add(bn);
                    //Toast.makeText(getApplicationContext(),xx,Toast.LENGTH_LONG).show();
                }
            }



            catch (Exception e)
            {
                Log.v("ee",""+e);
            }

            return listb;

        }


        @Override
        protected void onPostExecute(List<Bank> banks) {
            super.onPostExecute(banks);

            for(Iterator<Bank> i = banks.iterator(); i.hasNext(); ) {
                Toast.makeText(getActivity(),i.toString(),Toast.LENGTH_LONG).show();
            }



        }
*/
























