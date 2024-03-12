package com.test.products.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.products.domain.GetProductsUseCase
import com.test.products.domain.models.Product
import com.test.products.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun getProducts() {
        getProductsUseCase()
            .flowOn(Dispatchers.IO)
            .onEach { resource ->
                when(resource) {
                    is Resource.Error -> Unit // Some logic
                    Resource.Loading -> Unit // Some logic
                    is Resource.Success -> {
                        _state.update { it.copy(products = resource.model) }
                    }
                }
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    data class State(
        val products: List<Product> = emptyList()
    )

}