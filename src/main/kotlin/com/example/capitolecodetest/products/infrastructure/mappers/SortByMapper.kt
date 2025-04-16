package com.example.capitolecodetest.products.infrastructure.mappers

import com.example.capitolecodetest.products.application.enums.SortBy

object SortByMapper {
    fun toColumnName(sortBy: SortBy): String {
        return when (sortBy) {
            SortBy.SKU -> "sku"
            SortBy.PRICE -> "price"
            SortBy.DESCRIPTION -> "description"
            SortBy.CATEGORY -> "category"
        }
    }
}