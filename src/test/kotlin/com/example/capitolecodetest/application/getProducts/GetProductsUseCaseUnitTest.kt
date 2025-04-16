package com.example.capitolecodetest.application.getProducts

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.application.getProducts.GetProductsUseCase
import com.example.capitolecodetest.products.application.service.DiscountService
import com.example.capitolecodetest.products.domain.contracts.ProductsContract
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import com.example.capitolecodetest.products.infrastructure.response.ProductResponse
import com.example.capitolecodetest.shared.exceptions.InvalidPaginationException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class GetProductsUseCaseUnitTest {

    @Mock
    lateinit var repository: ProductsContract

    @Mock
    lateinit var discountService: DiscountService

    @InjectMocks
    lateinit var getProductsUseCase: GetProductsUseCase

    companion object {
        private const val PAGE = 0
        private const val SIZE = 50
        private const val INVALID_PAGINATION_MESSAGE = "Page and size must be positive numbers"
        private val product = Product("sku", 100.0, "product", ProductCategory.ELECTRONICS)
        private val product2 = Product("sku2", 120.89, "product2", ProductCategory.HOME_KITCHEN)
        private val productResponse = ProductResponse("sku", 100.0, 85.00, "product", ProductCategory.ELECTRONICS.value)
        private val productResponse2 =
            ProductResponse("sku2", 120.89, 84.62, "product2", ProductCategory.HOME_KITCHEN.value)
        private val productsResult = listOf(product, product2)

    }


    @Test
    fun `should return products with discount when category filter is null`() {

        `when`(repository.findAllProducts(PAGE, SIZE, SortBy.PRICE, Order.ASC, null)).thenReturn(productsResult)
        `when`(discountService.calculateDiscount(product)).thenReturn(0.15)
        `when`(discountService.calculateDiscount(product2)).thenReturn(0.30)

        val results = getProductsUseCase.invoke(PAGE, SIZE, SortBy.PRICE, Order.ASC, null)

        assertNotNull(results)
        assertEquals(2, results.size)

        assertEquals(productResponse.sku, results[0].sku)
        assertEquals(productResponse.price, results[0].price)
        assertEquals(productResponse.priceWithDiscount, results[0].priceWithDiscount)
        assertEquals(productResponse.description, results[0].description)
        assertEquals(productResponse.category, results[0].category)

        assertEquals(productResponse2.sku, results[1].sku)
        assertEquals(productResponse2.price, results[1].price)
        assertEquals(productResponse2.priceWithDiscount, results[1].priceWithDiscount)
        assertEquals(productResponse2.description, results[1].description)
        assertEquals(productResponse2.category, results[1].category)
    }

    @Test
    fun `should throw InvalidPaginationException when page is less than 0`() {
        val exception = assertFailsWith<InvalidPaginationException> {
            getProductsUseCase(
                page = -1, // page < 0
                size = 10,
                sortBy = SortBy.SKU,
                order = Order.ASC,
                categoryFilter = listOf(ProductCategory.ELECTRONICS)
            )
        }

        assert(exception.message == INVALID_PAGINATION_MESSAGE)
    }

    @Test
    fun `should throw InvalidPaginationException when size is less or 0`() {
        val exception = assertFailsWith<InvalidPaginationException> {
            getProductsUseCase(
                page = 0,
                size = 0, // size <= 0
                sortBy = SortBy.SKU,
                order = Order.ASC,
                categoryFilter = listOf(ProductCategory.ELECTRONICS)
            )
        }

        assert(exception.message == INVALID_PAGINATION_MESSAGE)
    }
}