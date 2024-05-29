package com.proyect.worldflags.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
