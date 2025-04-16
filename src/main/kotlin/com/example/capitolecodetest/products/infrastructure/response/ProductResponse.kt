package com.example.capitolecodetest.products.infrastructure.response

data class ProductResponse(
    val sku: String,
    val price: Double,
    val priceWithDiscount: Double,
    val description: String,
    val category: String
)