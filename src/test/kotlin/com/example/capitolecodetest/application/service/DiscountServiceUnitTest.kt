package com.example.capitolecodetest.application.service

import com.example.capitolecodetest.products.application.service.DiscountService
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class DiscountServiceUnitTest {

    var discountService: DiscountService = DiscountService()

    companion object {
        private const val ELECTRONICS_DISCOUNT = 0.15
        private const val SKU_DISCOUNT = 0.30
        private const val NO_DISCOUNT = 0.00
    }


    @Test
    fun `should apply category discount when product matches category`() {

        val product = Product(
            sku = "sku",
            price = 200.0,
            description = "description",
            category = ProductCategory.ELECTRONICS
        )

        val discount = discountService.calculateDiscount(product)

        assertEquals(ELECTRONICS_DISCOUNT, discount)
    }

    @Test
    fun `should apply sku discount when sku ends with 5`() {

        val product = Product(
            sku = "sku5",
            price = 100.0,
            description = "description",
            category = ProductCategory.SPORTS
        )

        val discount = discountService.calculateDiscount(product)

        assertEquals(SKU_DISCOUNT, discount)
    }

    @Test
    fun `should apply highest discount`() {

        val product = Product(
            sku = "sku5",
            price = 300.0,
            description = "description",
            category = ProductCategory.HOME_KITCHEN
        )

        val discount = discountService.calculateDiscount(product)

        assertEquals(SKU_DISCOUNT, discount) // HOME_KITCHEN → 25%, SKU ends with 5 → 30% → pick max
    }

    @Test
    fun `should return zero when no one matches`() {

        val product = Product(
            sku = "sku",
            price = 50.0,
            description = "description",
            category = ProductCategory.SPORTS
        )

        val discount = discountService.calculateDiscount(product)

        assertEquals(NO_DISCOUNT, discount)
    }
}