package com.example.capitolecodetest.application.strategies

import com.example.capitolecodetest.products.application.strategies.CategoryDiscountStrategyImpl
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CategoryDiscountStrategyImplUnitTest {

    companion object {
        private const val ELECTRONICS_DISCOUNT = 0.15
        private const val NO_DISCOUNT = 0.00
    }

    val strategy: CategoryDiscountStrategyImpl =
        CategoryDiscountStrategyImpl(ProductCategory.ELECTRONICS, ELECTRONICS_DISCOUNT)


    @Test
    fun `should return discount when category matches`() {

        val product = Product(
            sku = "sku",
            price = 200.0,
            description = "description",
            category = ProductCategory.ELECTRONICS
        )

        val discount = strategy.calculateDiscount(product)

        assertEquals(ELECTRONICS_DISCOUNT, discount)
    }

    @Test
    fun `should return zero when category don't match`() {

        val product = Product(
            sku = "sku",
            price = 200.0,
            description = "description",
            category = ProductCategory.CLOTHING
        )

        val discount = strategy.calculateDiscount(product)

        assertEquals(NO_DISCOUNT, discount)
    }
}