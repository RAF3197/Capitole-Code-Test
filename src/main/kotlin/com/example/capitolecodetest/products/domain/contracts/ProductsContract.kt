package com.example.capitolecodetest.products.domain.contracts

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product

interface ProductsContract {
    fun findAllProducts(
        page: Int,
        size: Int,
        sortBy: SortBy,
        order: Order,
        categoryFilter: List<ProductCategory>?
    ): List<Product>
}