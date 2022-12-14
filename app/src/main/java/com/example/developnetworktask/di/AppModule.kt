package com.example.developnetworktask.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.developnetworktask.data.local.ProductDatabase
import com.example.developnetworktask.data.remote.ProductApi
import com.example.developnetworktask.data.repository.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            "product.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDataStoreRepo(@ApplicationContext context: Context) : DataStoreManager {
        return  DataStoreManager(context)
    }

}
