package com.example.capitolecodetest.products.infrastructure.persistance

import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.entities.ProductEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductsRepositoryInterface : JpaRepository<ProductEntity, String> {
    fun findByCategoryIn(categories: List<ProductCategory>, pageable: Pageable): List<ProductEntity>
}