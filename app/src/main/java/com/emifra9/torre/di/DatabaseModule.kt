package com.emifra9.torre.di

import android.content.Context
import androidx.room.Room
import com.emifra9.torre.data.TorreDao
import com.emifra9.torre.data.TorreDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object  DatabaseModule {

    @Provides
    fun provideTorreDao(appDatabase: TorreDb): TorreDao {
        return appDatabase.torreDb()
    }

    @Provides
    @Singleton
    fun provideTorreDatabase(@ApplicationContext appContext: Context): TorreDb {
        return Room.databaseBuilder(
            appContext,
            TorreDb::class.java,
            "torre-db"
        ).build()
    }

}