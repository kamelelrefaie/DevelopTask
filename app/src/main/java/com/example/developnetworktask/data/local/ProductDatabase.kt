package com.example.developnetworktask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductItemEntity::class], version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract val productDao: ProductDao
}