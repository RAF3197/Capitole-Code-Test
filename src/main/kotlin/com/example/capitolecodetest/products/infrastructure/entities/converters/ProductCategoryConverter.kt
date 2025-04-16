package com.example.capitolecodetest.products.infrastructure.entities.converters

import com.example.capitolecodetest.products.domain.enums.ProductCategory
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ProductCategoryConverter : AttributeConverter<ProductCategory, String> {
    override fun convertToDatabaseColumn(attribute: ProductCategory?): String? {
        return attribute?.value
    }

    override fun convertToEntityAttribute(dbData: String?): ProductCategory? {
        return ProductCategory.entries.find { it.value == dbData }
    }
}