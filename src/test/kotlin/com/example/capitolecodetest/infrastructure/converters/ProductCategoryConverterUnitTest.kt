package com.example.capitolecodetest.infrastructure.converters

import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.entities.converters.ProductCategoryConverter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import kotlin.test.Test

class ProductCategoryConverterUnitTest {
    companion object {
        private const val ELECTRONICS = "Electronics"
        private const val HOME_KITCHEN = "Home & Kitchen"
        private const val CLOTHING = "Clothing"
        private const val ACCESSORIES = "Accessories"
        private const val SPORTS = "Sports"
        private const val MUSICAL_INSTRUMENT = "Musical Instrument"
        private const val FOOTWEAR = "Footwear"
        private const val HOME_APPLIANCES = "Home & Appliances"
        private const val STATIONERY = "Stationery"
        private const val TOYS_GAMES = "Toys & Games"
        private const val NON_EXISTING = "NonExistentCategory"
    }

    private val converter = ProductCategoryConverter()

    @Test
    fun `should convert enum to database column`() {

        assertEquals(ELECTRONICS, converter.convertToDatabaseColumn(ProductCategory.ELECTRONICS))
        assertEquals(HOME_KITCHEN, converter.convertToDatabaseColumn(ProductCategory.HOME_KITCHEN))
        assertEquals(CLOTHING, converter.convertToDatabaseColumn(ProductCategory.CLOTHING))
        assertEquals(ACCESSORIES, converter.convertToDatabaseColumn(ProductCategory.ACCESSORIES))
        assertEquals(SPORTS, converter.convertToDatabaseColumn(ProductCategory.SPORTS))
        assertEquals(MUSICAL_INSTRUMENT, converter.convertToDatabaseColumn(ProductCategory.MUSICAL_INSTR))
        assertEquals(FOOTWEAR, converter.convertToDatabaseColumn(ProductCategory.FOOTWEAR))
        assertEquals(HOME_APPLIANCES, converter.convertToDatabaseColumn(ProductCategory.HOME_APPLIANCES))
        assertEquals(STATIONERY, converter.convertToDatabaseColumn(ProductCategory.STATIONERY))
        assertEquals(TOYS_GAMES, converter.convertToDatabaseColumn(ProductCategory.TOYS_GAMES))
    }

    @Test
    fun `should convert database column to enum`() {

        assertEquals(ProductCategory.ELECTRONICS, converter.convertToEntityAttribute(ELECTRONICS))
        assertEquals(ProductCategory.HOME_KITCHEN, converter.convertToEntityAttribute(HOME_KITCHEN))
        assertEquals(ProductCategory.CLOTHING, converter.convertToEntityAttribute(CLOTHING))
        assertEquals(ProductCategory.ACCESSORIES, converter.convertToEntityAttribute(ACCESSORIES))
        assertEquals(ProductCategory.SPORTS, converter.convertToEntityAttribute(SPORTS))
        assertEquals(ProductCategory.MUSICAL_INSTR, converter.convertToEntityAttribute(MUSICAL_INSTRUMENT))
        assertEquals(ProductCategory.FOOTWEAR, converter.convertToEntityAttribute(FOOTWEAR))
        assertEquals(ProductCategory.HOME_APPLIANCES, converter.convertToEntityAttribute(HOME_APPLIANCES))
        assertEquals(ProductCategory.STATIONERY, converter.convertToEntityAttribute(STATIONERY))
        assertEquals(ProductCategory.TOYS_GAMES, converter.convertToEntityAttribute(TOYS_GAMES))
    }

    @Test
    fun `should return null when input is null`() {

        assertNull(converter.convertToDatabaseColumn(null))
        assertNull(converter.convertToEntityAttribute(null))
    }

    @Test
    fun `should return null when db value does not match any enum`() {

        assertNull(converter.convertToEntityAttribute(NON_EXISTING))
    }
}