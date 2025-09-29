package com.example.abito.di

import com.example.abito.data.remote.AbitoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAbitoApi(): AbitoApi {
        return Retrofit.Builder().baseUrl("https://api.abito.dev").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
            .create(AbitoApi::class.java)
    }
}