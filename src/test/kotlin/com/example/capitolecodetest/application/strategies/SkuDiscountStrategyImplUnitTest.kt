package com.example.capitolecodetest.application.strategies

import com.example.capitolecodetest.products.application.strategies.SkuDiscountStrategyImpl
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class SkuDiscountStrategyImplUnitTest {

    companion object {
        private const val SKU_ENDING = "5"
        private const val SKU_DISCOUNT = 0.30
        private const val NO_DISCOUNT = 0.00
    }

    val strategy: SkuDiscountStrategyImpl = SkuDiscountStrategyImpl(SKU_ENDING, SKU_DISCOUNT)


    @Test
    fun `should return discount when sku ending matches`() {

        val product = Product(
            sku = "sku5",
            price = 200.0,
            description = "description",
            category = ProductCategory.ELECTRONICS
        )

        val discount = strategy.calculateDiscount(product)

        assertEquals(SKU_DISCOUNT, discount)
    }

    @Test
    fun `should return zero when sku ending don't match`() {

        val product = Product(
            sku = "sku",
            price = 200.0,
            description = "description",
            category = ProductCategory.ELECTRONICS
        )

        val discount = strategy.calculateDiscount(product)

        assertEquals(NO_DISCOUNT, discount)
    }
}