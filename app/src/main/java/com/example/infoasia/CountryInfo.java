package com.example.infoasia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class CountryInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        //Direct Defining
        TextView countryNameTV = findViewById(R.id.countryName);
        TextView capitalNameTV = findViewById(R.id.capitalName);
        TextView regionTV = findViewById(R.id.region);
        TextView subregionTV = findViewById(R.id.subRegion);
        TextView population = findViewById(R.id.population);
        TextView languages = findViewById(R.id.languages);
        ArrayList<String> langList= new ArrayList<>();
        ListView langLV = findViewById(R.id.languagesListView);
        ListView bordLV = findViewById(R.id.bordersListView);
        ArrayList<String> bordList= new ArrayList<>();
        ImageView imageView = findViewById(R.id.flagImage);

        Intent intent = getIntent();
        JSONObject jsonObject;
        String objectValue = intent.getStringExtra("object");
        try {
            jsonObject = new JSONObject(objectValue);
            countryNameTV.setText("Country Name : "+jsonObject.getString("name"));
            capitalNameTV.setText("Capital : "+jsonObject.getString("capital"));
            regionTV.setText("Region : "+jsonObject.getString("region"));
            subregionTV.setText("Sub Region : "+jsonObject.getString("subregion"));
            population.setText("Population : "+jsonObject.getString("population"));
            GlideToVectorYou
                    .init()
                    .with(getApplicationContext())
                    .setPlaceHolder(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)
                    .load(Uri.parse(jsonObject.getString("flag")), imageView);
            JSONArray jsonarray = new JSONArray(jsonObject.getString("languages"));
            for(int i=0;i<jsonarray.length();i++){
                JSONObject langObject = jsonarray.getJSONObject(i);
                langList.add(langObject.getString("name"));
            }
            JSONArray jsonarray2 = new JSONArray(jsonObject.getString("borders"));
            for(int i=0;i<jsonarray2.length();i++){
                bordList.add(jsonarray2.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,langList);
        langLV.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,bordList);
        bordLV.setAdapter(adapter2);
    }
}