package com.example.capitolecodetest.products.infrastructure.request

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import io.swagger.v3.oas.annotations.media.Schema

data class GetProductsRequest(
    @Schema(description = "Results page number", example = "0")
    val page: Int = 0,
    @Schema(description = "Number of results per page", example = "50")
    val size: Int = 50,
    @Schema(
        description = "Field to sort the results with",
        example = "SKU, " +
                "PRICE," +
                " DESCRIPTION," +
                " CATEGORY"
    )
    val sortBy: SortBy = SortBy.SKU,
    @Schema(description = "Order of the results, can be ASC or DESC", example = "ASC")
    val order: Order = Order.ASC,
    @Schema(
        description = "Category filter, accepts one or more categories to filter with",
        example = "ELECTRONICS," +
                "HOME_KITCHEN," +
                "CLOTHING," +
                "ACCESSORIES," +
                "SPORTS," +
                "MUSICAL_INSTR," +
                "FOOTWEAR," +
                "HOME_APPLIANCES," +
                "STATIONERY," +
                "TOYS_GAMES"
    )
    val categoryFilter: List<ProductCategory>? = null
)