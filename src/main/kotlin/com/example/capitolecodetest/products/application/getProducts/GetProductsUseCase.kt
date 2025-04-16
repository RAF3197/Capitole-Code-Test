package com.example.capitolecodetest.products.application.getProducts

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.application.service.DiscountService
import com.example.capitolecodetest.products.domain.contracts.ProductsContract
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.response.ProductResponse
import com.example.capitolecodetest.shared.exceptions.InvalidPaginationException
import com.example.capitolecodetest.shared.utils.roundToTwoDecimalPlaces
import org.springframework.stereotype.Service

@Service
class GetProductsUseCase(
    private val repository: ProductsContract,
    private val discountService: DiscountService
) {
    operator fun invoke(
        page: Int,
        size: Int,
        sortBy: SortBy,
        order: Order,
        categoryFilter: List<ProductCategory>?
    ): List<ProductResponse> {

        if (page < 0 || size <= 0) {
            throw InvalidPaginationException("Page and size must be positive numbers")
        }

        val products = repository.findAllProducts(page, size, sortBy, order, categoryFilter)
        return products.map { p ->
            val discount = discountService.calculateDiscount(p)
            val discountedPrice = p.price * (1 - discount)
            ProductResponse(
                sku = p.sku,
                price = p.price,
                priceWithDiscount = discountedPrice.roundToTwoDecimalPlaces(),
                description = p.description,
                category = p.category.value
            )
        }
    }
}