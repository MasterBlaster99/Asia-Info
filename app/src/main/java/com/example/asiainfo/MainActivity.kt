package com.example.asiainfo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {
    var objectsList = ArrayList<JSONObject>()
    var list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        val db = Room.databaseBuilder(applicationContext, Database::class.java, "DATABASE").allowMainThreadQueries().build()
        val url = "https://restcountries.eu/rest/v2/region/asia"
        val button = findViewById<FloatingActionButton>(R.id.deleteBtn)
        val refButton = findViewById<FloatingActionButton>(R.id.refreshBtn)

        refButton.setOnClickListener {
            val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
                    { response ->
                        try {
                            val size = response.length()
                            for (i in 0 until size) {
                                val `object` = response.getJSONObject(i)
                                objectsList.add(`object`)
                                val cName = `object`.getString("name")
                                val country = Country()
                                if (list.isEmpty()) {
                                    country.setId(i)
                                    country.setName(cName)
                                    country.setCapitalName(`object`.getString("capital"))
                                    country.setBorders(`object`.getString("borders"))
                                    country.setLanguages(`object`.getString("languages"))
                                    country.setPopulation("population")
                                    country.setRegion("region")
                                    country.setSubRegion("subregion")
                                    db.dao()?.addCountry(country)
                                    finish()
                                    startActivity(intent)
                                }
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }) { Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_LONG).show() }
            queue.add(jsonObjectRequest)
        }

        val countries: List<Country> = db.dao()?.getCountries() as List<Country>
        for (countryObject in countries) {
            Log.d("Naam", countryObject.getName()!!)
            list.add(countryObject.getName()!!)
        }

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        val lv = findViewById<ListView>(R.id.countryList)
        lv.adapter = adapter

        lv.onItemClickListener = OnItemClickListener { adapterView, view, i, l ->
            try {
                val intent = Intent(applicationContext, CountryInfo::class.java)
                val ob: JSONObject = objectsList.get(i)
                intent.putExtra("object", ob.toString())
                Log.d("TAG", ob.toString())
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Please try to refresh again", Toast.LENGTH_LONG).show()
            }
        }

        button.setOnClickListener {
            for (country1 in countries) {
                val country = Country()
                country.setId(country1.getId())
                db.dao()!!.deleteCountry(country)
            }
            finish()
            startActivity(intent)
        }
    }
}