package com.example.capitolecodetest.products.application.service

import com.example.capitolecodetest.products.application.strategies.CategoryDiscountStrategyImpl
import com.example.capitolecodetest.products.application.strategies.SkuDiscountStrategyImpl
import com.example.capitolecodetest.products.domain.contracts.DiscountStrategyContract
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import org.springframework.stereotype.Service

@Service
class DiscountService {

    private val strategies: List<DiscountStrategyContract> = listOf(
        CategoryDiscountStrategyImpl(ProductCategory.ELECTRONICS, 0.15),
        CategoryDiscountStrategyImpl(ProductCategory.HOME_KITCHEN, 0.25),
        SkuDiscountStrategyImpl("5", 0.30)
    )

    fun calculateDiscount(product: Product): Double {
        val discounts = strategies.map { it.calculateDiscount(product) }
        return discounts.maxOrNull() ?: 0.0
    }
}