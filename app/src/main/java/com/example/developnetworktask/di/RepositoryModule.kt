package com.example.developnetworktask.di

import com.example.developnetworktask.data.repository.ProductRepositoryImpl
import com.example.developnetworktask.domain.repository.ProductRepository
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
    abstract fun bindWeatherRepository(
        repositoryImpl: ProductRepositoryImpl
    ): ProductRepository
}