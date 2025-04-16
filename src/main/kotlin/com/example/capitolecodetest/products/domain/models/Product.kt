package com.example.capitolecodetest.products.domain.models

import com.example.capitolecodetest.products.domain.enums.ProductCategory

data class Product(
    val sku: String,
    val price: Double,
    val description: String,
    val category: ProductCategory,
)