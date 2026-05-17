package com.example.abito.di

import com.example.abito.data.local.TokenRepositoryImpl
import com.example.abito.domain.auth.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideTokenRepository(
        impl: TokenRepositoryImpl
    ): TokenRepository = impl
}