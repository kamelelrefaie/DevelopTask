package com.example.developnetworktask.data.repository

import com.example.developnetworktask.data.local.ProductDatabase
import com.example.developnetworktask.data.mapper.toProductItem
import com.example.developnetworktask.data.mapper.toProductItemEntity
import com.example.developnetworktask.data.remote.ProductApi
import com.example.developnetworktask.domain.model.ProductItem
import com.example.developnetworktask.domain.repository.ProductRepository
import com.example.developnetworktask.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val db: ProductDatabase
) : ProductRepository {

    private val dao = db.productDao
    override suspend fun getProducts(
        fetchFromRemote: Boolean,
        token: String
    ): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading(true))

        val productList = dao.getProductList()
        emit(Resource.Success(productList.map {
            it.toProductItem()
        }))

        val shouldLoadFromCache = productList.isNotEmpty() && !fetchFromRemote
        if (shouldLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }

        val remoteProductList = try {
            val response = api.getProducts(token)
            //caching into database
            dao.clearProductList()
            dao.insertProductList(response.products.map { it.toProductItemEntity() })
            //emit new values
            emit(Resource.Success(data = dao.getProductList().map { it.toProductItem() }))
            emit(Resource.Loading(false))
            //parsing is wrong
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
            //invalid response
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error("Couldn't load data"))
        }

    }

}

