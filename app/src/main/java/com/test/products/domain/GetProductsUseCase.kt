package com.test.products.domain

import com.test.products.domain.models.Product
import com.test.products.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    operator fun invoke(): Flow<Resource<List<Product>>> {
        return productsRepository.getProducts()
    }

}