package com.example.capitolecodetest.products.infrastructure.mappers

import com.example.capitolecodetest.products.domain.models.Product
import com.example.capitolecodetest.products.infrastructure.entities.ProductEntity
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface ProductMapper {
    fun toProduct(productEntity: ProductEntity): Product
    fun toProductEntity(product: Product): ProductEntity
}