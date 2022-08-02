package com.example.developnetworktask.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(productListEntity: List<ProductItemEntity>)

    @Query("DELETE FROM ProductItemEntity")
    suspend fun clearProductList()

    @Query("SELECT * FROM ProductItemEntity")
    suspend fun getProductList(): List<ProductItemEntity>
}