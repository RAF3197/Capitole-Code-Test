package com.example.capitolecodetest.infrastructure.mappers

import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.infrastructure.mappers.SortByMapper
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class SortByMapperUnitTest {

    companion object {
        private const val SKU = "sku"
        private const val PRICE = "price"
        private const val DESCRIPTION = "description"
        private const val CATEGORY = "category"
    }

    @Test
    fun `should map SortBy SKU to column name sku`() {

        val sortBy = SortBy.SKU

        val result = SortByMapper.toColumnName(sortBy)

        assertEquals(SKU, result)
    }

    @Test
    fun `should map SortBy PRICE to column name price`() {

        val sortBy = SortBy.PRICE

        val result = SortByMapper.toColumnName(sortBy)

        assertEquals(PRICE, result)
    }

    @Test
    fun `should map SortBy DESCRIPTION to column name description`() {

        val sortBy = SortBy.DESCRIPTION

        val result = SortByMapper.toColumnName(sortBy)

        assertEquals(DESCRIPTION, result)
    }

    @Test
    fun `should map SortBy CATEGORY to column name category`() {

        val sortBy = SortBy.CATEGORY

        val result = SortByMapper.toColumnName(sortBy)

        assertEquals(CATEGORY, result)
    }
}