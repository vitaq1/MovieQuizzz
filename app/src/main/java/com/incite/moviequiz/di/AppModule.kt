package com.incite.moviequiz.di

import android.app.Application
import androidx.room.Room
import com.incite.moviequiz.data.local.MovieDao
import com.incite.moviequiz.data.local.MovieDatabase
import com.incite.moviequiz.data.repository.MovieRepositoryImpl
import com.incite.moviequiz.domain.model.DataLoader
import com.incite.moviequiz.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideDataLoader(repo: MovieRepositoryImpl): DataLoader {
        return DataLoader(repo)
    }

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app, MovieDatabase::class.java, "movie_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}