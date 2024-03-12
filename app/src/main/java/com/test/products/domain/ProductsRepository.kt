package com.test.products.domain

import com.test.products.domain.models.Product
import com.test.products.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProducts(): Flow<Resource<List<Product>>>

}