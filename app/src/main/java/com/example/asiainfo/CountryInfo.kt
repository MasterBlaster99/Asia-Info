package com.example.asiainfo

import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class CountryInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)
        //Direct Defining
        val countryNameTV = findViewById<TextView>(R.id.countryName)
        val capitalNameTV = findViewById<TextView>(R.id.capitalName)
        val regionTV = findViewById<TextView>(R.id.region)
        val subregionTV = findViewById<TextView>(R.id.subRegion)
        val population = findViewById<TextView>(R.id.population)
        val languages = findViewById<TextView>(R.id.languages)
        val langList = ArrayList<String>()
        val langLV = findViewById<ListView>(R.id.languagesListView)
        val bordLV = findViewById<ListView>(R.id.bordersListView)
        val bordList = ArrayList<String>()
        val imageView = findViewById<ImageView>(R.id.flagImage)

        val intent = intent
        val jsonObject: JSONObject
        val objectValue = intent.getStringExtra("object")
        try {
            jsonObject = JSONObject(objectValue)
            countryNameTV.text = "Country Name : " + jsonObject.getString("name")
            capitalNameTV.text = "Capital : " + jsonObject.getString("capital")
            regionTV.text = "Region : " + jsonObject.getString("region")
            subregionTV.text = "Sub Region : " + jsonObject.getString("subregion")
            population.text = "Population : " + jsonObject.getString("population")
            GlideToVectorYou
                .init()
                .with(applicationContext)
                .setPlaceHolder(
                    R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background
                )
                .load(Uri.parse(jsonObject.getString("flag")), imageView)
            val jsonarray = JSONArray(jsonObject.getString("languages"))
            for (i in 0 until jsonarray.length()) {
                val langObject = jsonarray.getJSONObject(i)
                langList.add(langObject.getString("name"))
            }
            val jsonarray2 = JSONArray(jsonObject.getString("borders"))
            for (i in 0 until jsonarray2.length()) {
                bordList.add(jsonarray2.getString(i))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, langList)
        langLV.adapter = adapter
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, bordList)
        bordLV.adapter = adapter2
    }
}