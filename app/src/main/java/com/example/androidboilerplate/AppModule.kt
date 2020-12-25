package com.example.androidboilerplate

import com.example.androidboilerplate.network.SampleApi
import com.example.androidboilerplate.network.SampleDataSource
import com.example.androidboilerplate.repositories.SampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSampleApi() = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/photos")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SampleApi::class.java)

    @Singleton
    @Provides
    fun provideSampleDataSource(sampleApi: SampleApi) =
        SampleDataSource(sampleApi)

    @Singleton
    @Provides
    fun provideSampleRepository(sampleDataSource: SampleDataSource) =
        SampleRepository(sampleDataSource)
}