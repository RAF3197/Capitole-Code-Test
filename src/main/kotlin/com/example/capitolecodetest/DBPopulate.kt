package com.example.capitolecodetest

import com.example.capitolecodetest.products.domain.models.Product
import com.example.capitolecodetest.products.infrastructure.mappers.ProductMapper
import com.example.capitolecodetest.products.infrastructure.persistance.ProductsRepositoryInterface
import com.example.capitolecodetest.shared.exceptions.InvalidPriceException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DBPopulate(
    private val repository: ProductsRepositoryInterface,
    private val jacksonObjectMapper: ObjectMapper,
    private val mapper: ProductMapper
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {

        val inputStream = javaClass.classLoader.getResourceAsStream("db/data/products.json")
        val products: List<Product> = jacksonObjectMapper.readValue(inputStream)
        for (product in products) {
            try {
                if (validate(product)) {
                    repository.saveAndFlush(mapper.toProductEntity(product))
                }
            } catch (e: Exception) {
                print(e.message)
            }
        }
    }

    private fun validate(product: Product): Boolean {

        if (product.price <= 0)
            throw InvalidPriceException("The product with SKU: ${product.sku} must have a price greater than 0\n")

        if (!repository.findById(product.sku).isEmpty) return true

        return false
    }
}