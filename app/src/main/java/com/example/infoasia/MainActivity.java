package com.example.infoasia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<JSONObject> objectsList = new ArrayList<>();
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        Database db = Room.databaseBuilder(getApplicationContext(),Database.class,"DATABASE").allowMainThreadQueries().build();
        String url ="https://restcountries.eu/rest/v2/region/asia";
        FloatingActionButton button = findViewById(R.id.deleteBtn);
        FloatingActionButton refButton = findViewById(R.id.refreshBtn);

        refButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            int size = response.length();
                            for(int i=0;i<size;i++){
                                JSONObject object = response.getJSONObject(i);
                                objectsList.add(object);
                                String cName=object.getString("name");
                                    Country country = new Country();
                                    if(list.isEmpty()) {
                                        country.setId(i);
                                        country.setName(cName);
                                        country.setCapitalName(object.getString("capital"));
                                        country.setBorders(object.getString("borders"));
                                        country.setLanguages(object.getString("languages"));
                                        country.setPopulation("population");
                                        country.setRegion("region");
                                        country.setSubRegion("subregion");
                                        db.dao().addCountry(country);
                                        finish();
                                        startActivity(getIntent());
                                    }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
            }
        });

        List<Country> countries = db.dao().getCountries();
        for(Country countryObject : countries){
           Log.d("Naam",countryObject.getName());
           list.add(countryObject.getName());
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        ListView lv = findViewById(R.id.countryList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Intent intent = new Intent(getApplicationContext(), CountryInfo.class);
                    JSONObject ob = objectsList.get(i);
                    intent.putExtra("object", ob.toString());
                    Log.d("TAG", ob.toString());
                    startActivity(intent);
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Please try to refresh again", Toast.LENGTH_LONG).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Country country1 : countries){
                    Country country = new Country();
                    country.setId(country1.getId());
                    db.dao().deleteCountry(country);
                }
                finish();
                startActivity(getIntent());
            }
        });
    }
}