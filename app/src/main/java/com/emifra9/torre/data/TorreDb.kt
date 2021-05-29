package com.emifra9.torre.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1,exportSchema = true)
abstract class TorreDb : RoomDatabase() {
    abstract fun torreDb(): TorreDao
}