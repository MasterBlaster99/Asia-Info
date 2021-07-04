package com.example.asiainfo

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert
    fun addCountry(country: Country?)

    @Update
    fun update(country: Country?)

    @Query("select * from countries")
    fun getCountries(): List<Country?>?

    @Delete
    fun deleteCountry(country: Country?)
}