package com.example.capitolecodetest.products.infrastructure.mappers

import com.example.capitolecodetest.products.application.enums.Order
import org.springframework.data.domain.Sort

object OrderMapper {
    fun toJpaDirection(order: Order): Sort.Direction {
        return when (order) {
            Order.ASC -> Sort.Direction.ASC
            Order.DESC -> Sort.Direction.DESC
        }
    }
}