package com.test.products.data

import com.test.products.data.mappers.toProducts
import com.test.products.data.models.ProductDto
import com.test.products.domain.ProductsRepository
import com.test.products.domain.models.Product
import com.test.products.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(): ProductsRepository {
    override fun getProducts(): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading)
            try {
                // Fake api call
                val response = listOf(
                    ProductDto(0, "Product 1"),
                    ProductDto(1, "Product 2"),
                    ProductDto(2, "Product 3")
                )
                val products = response.toProducts()
                emit(Resource.Success(products))
            } catch (t: Throwable) {
                val exception = when (t) {
                    is UnknownHostException -> {
                        Exception("Can not connect to network")
                    }
                    else -> {
                        Exception("Could not fetch from network")
                    }
                }
                emit(Resource.Error(exception = exception, null))
            }
        }
    }
}