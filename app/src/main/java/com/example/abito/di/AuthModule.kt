package com.example.abito.di

import com.example.abito.data.auth.TokenRepositoryImpl
import com.example.abito.domain.auth.LoginUseCase
import com.example.abito.domain.auth.TokenRepository
import com.example.abito.domain.repository.AbitoRepository
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

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: AbitoRepository,
        tokenRepository: TokenRepository
    ): LoginUseCase = LoginUseCase(repository, tokenRepository)
}