package com.test.products.presentation.products.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.products.domain.models.Product

class ProductsListDiffCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>,
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}