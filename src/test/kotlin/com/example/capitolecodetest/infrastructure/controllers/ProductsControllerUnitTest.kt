package com.example.capitolecodetest.infrastructure.controllers

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.application.getProducts.GetProductsUseCase
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.controllers.ProductsController
import com.example.capitolecodetest.products.infrastructure.response.ProductResponse
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.test.Test

@WebMvcTest(ProductsController::class)
class ProductsControllerUnitTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var getProductsUseCase: GetProductsUseCase

    companion object {
        private const val PAGE = 0
        private const val SIZE = 50
        private const val SORT = "SKU"
        private const val ORDER = "ASC"
        private const val CATEGORY_FILTER = "ELECTRONICS"

        private val productResponse = ProductResponse(
            sku = "sku001",
            price = 100.0,
            priceWithDiscount = 90.0,
            description = "A sample product",
            category = "ELECTRONICS"
        )

        private val productResponseList = listOf(productResponse)
    }

    @Test
    fun `should return products list when valid request is made`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf(ProductCategory.ELECTRONICS)
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .param("sortBy", SORT)
                .param("order", ORDER)
                .param("categoryFilter", CATEGORY_FILTER)
        )
    }

    @Test
    fun `should return products list when no page supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf(ProductCategory.ELECTRONICS)
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("size", SIZE.toString())
                .param("sortBy", SORT)
                .param("order", ORDER)
                .param("categoryFilter", CATEGORY_FILTER)
        )
    }

    @Test
    fun `should return products list when no size supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf(ProductCategory.ELECTRONICS)
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("sortBy", SORT)
                .param("order", ORDER)
                .param("categoryFilter", CATEGORY_FILTER)
        )
    }

    @Test
    fun `should return products list when no sortBy supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf(ProductCategory.ELECTRONICS)
            )
        )
            .thenReturn(productResponseList)  // Mock de la invocación

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .param("order", ORDER)
                .param("categoryFilter", CATEGORY_FILTER)
        )
    }

    @Test
    fun `should return products list when no order supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf(ProductCategory.ELECTRONICS)
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .param("sortBy", SORT)
                .param("categoryFilter", CATEGORY_FILTER)
        )
    }

    @Test
    fun `should return products list when no categoryFilter supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                null
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .param("sortBy", SORT)
                .param("order", ORDER)
        )
    }

    @Test
    fun `should return products list when no params supplied`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                null
            )
        )
            .thenReturn(productResponseList)

        doProductRequest(MockMvcRequestBuilders.get("/products"))
    }

    @Test
    fun `should return empty list when no products are found`() {

        `when`(
            getProductsUseCase.invoke(
                PAGE,
                SIZE,
                SortBy.SKU,
                Order.ASC,
                listOf()
            )
        )
            .thenReturn(emptyList())

        mockMvc.perform(
            MockMvcRequestBuilders.get("/products")
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .param("sortBy", SORT)
                .param("order", ORDER)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty)  // Verificamos que la respuesta esté vacía
    }

    private fun doProductRequest(mock: MockHttpServletRequestBuilder) {

        mockMvc.perform(
            mock
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].sku")
                    .value(productResponse.sku)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].price")
                    .value(productResponse.price)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].priceWithDiscount")
                    .value(productResponse.priceWithDiscount)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].description")
                    .value(productResponse.description)
            )
            .andExpect(
                MockMvcResultMatchers.jsonPath("$[0].category")
                    .value(productResponse.category)
            )
    }
}