package com.example.capitolecodetest.infrastructure.mappers

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.infrastructure.mappers.OrderMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.data.domain.Sort
import kotlin.test.Test

class OrderMapperUnitTest {

    @Test
    fun `should map Order ASC`() {

        val order = Order.ASC

        val result = OrderMapper.toJpaDirection(order)

        assertEquals(Sort.Direction.ASC, result)
    }

    @Test
    fun `should map Order DESC`() {

        val order = Order.DESC

        val result = OrderMapper.toJpaDirection(order)

        assertEquals(Sort.Direction.DESC, result)
    }
}