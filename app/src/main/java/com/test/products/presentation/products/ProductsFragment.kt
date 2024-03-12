package com.test.products.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.products.databinding.FragmentProductsBinding
import com.test.products.presentation.products.adapter.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductsFragment: Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding
        get() = _binding ?: throw IllegalStateException("FragmentProductsBinding is null")

    private val viewModel: ProductsViewModel by viewModels()

    private val productsAdapter by lazy { ProductsAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        collectState()
        viewModel.getProducts()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupAdapter() = with(binding) {
        rvProducts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvProducts.adapter = productsAdapter
    }

    private fun collectState() {
        viewModel.state
            .onEach {
                productsAdapter.submitList(it.products)
            }
            .launchIn(lifecycleScope)
    }

}