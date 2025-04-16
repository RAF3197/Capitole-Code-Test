package com.example.capitolecodetest.products.domain.contracts

import com.example.capitolecodetest.products.domain.models.Product

interface DiscountStrategyContract {
    fun calculateDiscount(product: Product): Double
}