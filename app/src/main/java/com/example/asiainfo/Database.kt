package com.example.asiainfo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Country::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun dao(): Dao?
}