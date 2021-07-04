package com.example.asiainfo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
class Country {
    @PrimaryKey
    var id = 0

    @ColumnInfo(name = "countryName")
    var name: String? = null

    @ColumnInfo(name = "capitalName")
    var capitalName: String? = null

    @ColumnInfo(name = "region")
    var region: String? = null

    @ColumnInfo(name = "subRegion")
    var subRegion: String? = null

    @ColumnInfo(name = "population")
    var population: String? = null

    @ColumnInfo(name = "languages")
    var languages: String? = null

    @ColumnInfo(name = "borders")
    var borders: String? = null


    @JvmName("getId1")
    fun getId(): Int {
        return id
    }

    @JvmName("getName1")
    fun getName(): String? {
        return name
    }

    @JvmName("setId1")
    fun setId(id: Int) {
        this.id = id
    }

    @JvmName("setName1")
    fun setName(name: String?) {
        this.name = name
    }

    @JvmName("getCapitalName1")
    fun getCapitalName(): String? {
        return capitalName
    }

    @JvmName("getRegion1")
    fun getRegion(): String? {
        return region
    }

    @JvmName("getSubRegion1")
    fun getSubRegion(): String? {
        return subRegion
    }

    @JvmName("getPopulation1")
    fun getPopulation(): String? {
        return population
    }

    @JvmName("getLanguages1")
    fun getLanguages(): String? {
        return languages
    }

    @JvmName("getBorders1")
    fun getBorders(): String? {
        return borders
    }

    @JvmName("setCapitalName1")
    fun setCapitalName(capitalName: String?) {
        this.capitalName = capitalName
    }

    @JvmName("setRegion1")
    fun setRegion(region: String?) {
        this.region = region
    }

    @JvmName("setSubRegion1")
    fun setSubRegion(subRegion: String?) {
        this.subRegion = subRegion
    }

    @JvmName("setPopulation1")
    fun setPopulation(population: String?) {
        this.population = population
    }

    @JvmName("setLanguages1")
    fun setLanguages(languages: String?) {
        this.languages = languages
    }

    @JvmName("setBorders1")
    fun setBorders(borders: String?) {
        this.borders = borders
    }
}
