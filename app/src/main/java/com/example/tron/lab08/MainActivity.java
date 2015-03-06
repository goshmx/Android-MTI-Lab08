package com.example.tron.lab08;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public ArrayList<Ubicaciones> items = new ArrayList<Ubicaciones>();

    String TAG = "JSON OPERATIONS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        Json jsonrequest = new Json();
        String request = null;
        try {
            jsonrequest.execute();
            request = (String) jsonrequest.get();

        }
        catch (Exception ex ){
            ex.printStackTrace();
        }

        Log.d("JSON REQUEST", request);

        try {
            JSONArray json = new JSONArray(request);
            Log.e(TAG, "JSON objects lenght "+String.valueOf(json.length()));
            for(int i=0; i< json.length();i++) {
                JSONObject item = json.getJSONObject(i);

                Ubicaciones ubicacion = new Ubicaciones();
                ubicacion.setUbicacion(item.getString("name"));
                ubicacion.setDetalles(item.getString("descripcion"));
                items.add(ubicacion);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final myAdapter adapter = new myAdapter(getApplicationContext(),items);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(),items.get(position).getUbicacion() + " selected.",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}


