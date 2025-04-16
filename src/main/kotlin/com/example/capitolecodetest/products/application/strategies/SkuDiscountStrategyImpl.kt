package com.example.capitolecodetest.products.application.strategies

import com.example.capitolecodetest.products.domain.contracts.DiscountStrategyContract
import com.example.capitolecodetest.products.domain.models.Product

class SkuDiscountStrategyImpl(
    private val skuEnding: String,
    private val discountPercentage: Double
) : DiscountStrategyContract {
    override fun calculateDiscount(product: Product): Double {
        return if (product.sku.endsWith(skuEnding)) discountPercentage else 0.0
    }
}