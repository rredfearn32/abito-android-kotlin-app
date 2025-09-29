package com.example.abito.di

import com.example.abito.data.repository.AbitoRepositoryImpl
import com.example.abito.domain.repository.AbitoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAbitoRepository(abitoRepositoryImpl: AbitoRepositoryImpl): AbitoRepository
}