package com.emifra9.torre.di

import com.emifra9.torre.repo.TorreRepository
import com.emifra9.torre.repo.TorreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
//Repositories will live same as the activity that requires them
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract  fun providesTorreRepository(repository: TorreRepositoryImpl): TorreRepository

}