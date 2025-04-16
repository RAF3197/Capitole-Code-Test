package com.example.capitolecodetest.products.infrastructure.entities

import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.entities.converters.ProductCategoryConverter


import jakarta.persistence.*

@Entity
@Table(name = "products", schema = "capitole_db")
class ProductEntity(

    @Id
    @Column(name = "sku", nullable = false, unique = true)
    var sku: String = "",

    @Column(nullable = false)
    var price: Double = 0.0,

    @Column(nullable = false)
    var description: String = "",

    @Convert(converter = ProductCategoryConverter::class)
    @Column(nullable = false)
    var category: ProductCategory = ProductCategory.ELECTRONICS
)