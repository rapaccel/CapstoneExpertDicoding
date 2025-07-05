package com.example.mycapstonesubmission.core.di

import android.content.Context
import androidx.room.Room
import com.example.mycapstonesubmission.core.data.source.local.room.MoviesDao
import com.example.mycapstonesubmission.core.data.source.local.room.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "Movies.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao=database.moviesDao()

}