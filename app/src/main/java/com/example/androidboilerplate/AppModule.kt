package com.example.androidboilerplate

import android.content.Context
import androidx.room.Room
import com.example.androidboilerplate.database.AppDatabase
import com.example.androidboilerplate.database.dao.SampleDao
import com.example.androidboilerplate.network.api.SampleApi
import com.example.androidboilerplate.network.datasource.SampleDataSource
import com.example.androidboilerplate.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSampleApi(): SampleApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SampleApi::class.java)

    @Singleton
    @Provides
    fun provideSampleDataSource(sampleApi: SampleApi) =
        SampleDataSource(sampleApi)

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()

    @Singleton
    @Provides
    fun provideSampleDao(appDatabase: AppDatabase) = appDatabase.sampleDao

    @Singleton
    @Provides
    fun provideSampleRepository(sampleDataSource: SampleDataSource, sampleDao: SampleDao) =
        SampleRepository(sampleDataSource, sampleDao)
}