package com.example.capitolecodetest.products.infrastructure.controllers

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.application.getProducts.GetProductsUseCase
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.infrastructure.response.ProductResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@ResponseBody
class ProductsController(
    private val products: GetProductsUseCase
) {
    @GetMapping
    fun getProduct(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "50") size: Int,
        @RequestParam(defaultValue = "SKU") sortBy: SortBy,
        @RequestParam(defaultValue = "ASC") order: Order,
        @RequestParam(required = false) categoryFilter: List<ProductCategory>?
    ): List<ProductResponse> {
        return products(
            page,
            size,
            sortBy,
            order,
            categoryFilter
        )
    }
}