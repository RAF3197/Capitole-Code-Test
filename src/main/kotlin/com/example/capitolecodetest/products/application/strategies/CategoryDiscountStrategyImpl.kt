package com.example.capitolecodetest.products.application.strategies

import com.example.capitolecodetest.products.domain.contracts.DiscountStrategyContract
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product

class CategoryDiscountStrategyImpl(
    private val category: ProductCategory,
    private val discountPercentage: Double
) : DiscountStrategyContract {
    override fun calculateDiscount(product: Product): Double {
        return if (product.category == category) discountPercentage else 0.0
    }
}