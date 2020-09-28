package com.example.aplicatiedeinventariat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Reparatii extends AppCompatActivity {


    public class GetJson extends AsyncTask<String, Void, String> {
        ListView lv = (ListView) findViewById(R.id.LV_reparatii);
        List<String> r = new ArrayList<>();


        @Override
        protected String doInBackground(String... strings) {
            String rezultat=null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                InputStream is = http.getInputStream();

                BufferedReader reader =  new BufferedReader(new InputStreamReader(is));

                String linie = null;
                StringBuilder builder = new StringBuilder();
                while((linie=reader.readLine())!=null) {
                    builder.append(linie);
                }
                //aici e tot JSON-ul
                String textTotal = builder.toString();

                JSONObject object=new JSONObject(textTotal);

                JSONArray vector=object.getJSONArray("reparatii imprimanta");
                for(int i=0;i<vector.length();i++) {
                    JSONObject repImp=vector.getJSONObject(i);
                    JSONObject adresa=repImp.getJSONObject("adresa");

                    rezultat="Nume firma: "+repImp.getString("nume firma")+ "\nNumar telefon: " +repImp.getString("numar telefon")+ "\nEmail: "+repImp.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("nr")+", oras: "+adresa.getString("oras");
                    r.add(rezultat);
                }

                JSONArray vector2=object.getJSONArray("reparatii device-uri electronice");
                for(int i=0;i<vector2.length();i++) {
                    JSONObject repDe=vector2.getJSONObject(i);
                    JSONObject adresa=repDe.getJSONObject("adresa");

                    rezultat="Nume firma: "+repDe.getString("nume firma")+ "\nNumar telefon: " +repDe.getString("numar telefon")+ "\nEmail: "+repDe.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("nr")+", oras: "+adresa.getString("oras");
                    r.add(rezultat);
                }

                JSONArray vector3=object.getJSONArray("service auto");
                for(int i=0;i<vector3.length();i++) {
                    JSONObject repDe=vector3.getJSONObject(i);
                    JSONObject adresa=repDe.getJSONObject("adresa");

                    rezultat="Nume firma: "+repDe.getString("nume firma")+ "\nNumar telefon: " +repDe.getString("numar telefon")+ "\nEmail: "+repDe.getString("email");
                    rezultat+="\nStrada: "+adresa.getString("strada")+", nr. "+adresa.getString("nr")+", oras: "+adresa.getString("oras");
                    r.add(rezultat);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparatii);

        GetJson json=new GetJson(){
            //@Override
            protected void onPostExecute(String s) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Reparatii.this, android.R.layout.simple_list_item_1, r);
                lv.setAdapter(adapter);
            }
        };
        json.execute("https://api.myjson.com/bins/jwzx0");
    }
    public void openMap(View view) {
        Intent it=new Intent(this,MapsActivity.class);
        startActivity(it);
    }
}
