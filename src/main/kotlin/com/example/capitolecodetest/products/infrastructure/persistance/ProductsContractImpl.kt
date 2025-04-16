package com.example.capitolecodetest.products.infrastructure.persistance

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.domain.contracts.ProductsContract
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import com.example.capitolecodetest.products.infrastructure.mappers.OrderMapper
import com.example.capitolecodetest.products.infrastructure.mappers.ProductMapper
import com.example.capitolecodetest.products.infrastructure.mappers.SortByMapper
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class ProductsContractImpl(
    private val repository: ProductsRepositoryInterface,
    private val mapper: ProductMapper
) : ProductsContract {
    override fun findAllProducts(
        page: Int,
        size: Int,
        sortBy: SortBy,
        order: Order,
        categoryFilter: List<ProductCategory>?
    ): List<Product> {

        val pageable: Pageable = PageRequest.of(
            page,
            size,
            Sort.by(
                OrderMapper.toJpaDirection(order),
                SortByMapper.toColumnName(sortBy)
            )
        )

        if (!categoryFilter.isNullOrEmpty()) {
            return repository.findByCategoryIn(
                categoryFilter, pageable
            ).stream().map(mapper::toProduct).toList()
        }

        return repository.findAll(pageable).stream().map(mapper::toProduct).toList()
    }
}