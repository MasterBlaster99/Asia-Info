package com.example.infoasia;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    public void addCountry(Country country);

    @Update
    public void update(Country country);

    @Query("select * from countries")
    public List<Country> getCountries();

    @Delete
    public void deleteCountry(Country country);
}
