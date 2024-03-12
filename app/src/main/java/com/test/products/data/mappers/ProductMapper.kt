package com.test.products.data.mappers

import com.test.products.data.models.ProductDto
import com.test.products.domain.models.Product

fun ProductDto.toProduct() = Product(id, title)

fun List<ProductDto>.toProducts() = map { it.toProduct() }