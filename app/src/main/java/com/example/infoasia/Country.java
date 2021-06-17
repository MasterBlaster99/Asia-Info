package com.example.infoasia;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class Country {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "countryName")
    private String name;

    @ColumnInfo(name = "capitalName")
    private String capitalName;

    @ColumnInfo(name = "region")
    private String region;

    @ColumnInfo(name = "subRegion")
    private String subRegion;

    @ColumnInfo(name = "population")
    private String population;

    @ColumnInfo(name = "languages")
    private String languages;

    @ColumnInfo(name = "borders")
    private String borders;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public String getRegion() {
        return region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public String getPopulation() {
        return population;
    }

    public String getLanguages() {
        return languages;
    }

    public String getBorders() {
        return borders;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }
}
