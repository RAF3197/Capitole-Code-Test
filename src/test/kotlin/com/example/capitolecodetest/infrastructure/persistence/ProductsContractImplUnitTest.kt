package com.example.capitolecodetest.infrastructure.persistence

import com.example.capitolecodetest.products.application.enums.Order
import com.example.capitolecodetest.products.application.enums.SortBy
import com.example.capitolecodetest.products.domain.enums.ProductCategory
import com.example.capitolecodetest.products.domain.models.Product
import com.example.capitolecodetest.products.infrastructure.entities.ProductEntity
import com.example.capitolecodetest.products.infrastructure.mappers.ProductMapper
import com.example.capitolecodetest.products.infrastructure.persistance.ProductsContractImpl
import com.example.capitolecodetest.products.infrastructure.persistance.ProductsRepositoryInterface
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.*


@ExtendWith(MockitoExtension::class)
class ProductsContractImplUnitTest {

    @Mock
    lateinit var repository: ProductsRepositoryInterface

    @Mock
    lateinit var productMapper: ProductMapper

    @InjectMocks
    lateinit var productsRepositoryImpl: ProductsContractImpl

    companion object {
        private const val PAGE = 0
        private const val SIZE = 30

        private val productCategoryFilter = listOf(ProductCategory.ELECTRONICS)
        private val productEntity = ProductEntity("sku", 100.0, "product", ProductCategory.ELECTRONICS)
        private val productEntity2 = ProductEntity("sku2", 120.89, "product2", ProductCategory.ELECTRONICS)
        private val product = Product("sku", 100.0, "product", ProductCategory.ELECTRONICS)
        private val product2 = Product("sku2", 120.89, "product2", ProductCategory.ELECTRONICS)

        private val pageable: Pageable = PageRequest.of(
            0,
            30,
            Sort.by(
                Sort.Direction.ASC,
                "sku"
            )
        )
    }

    @Test
    fun `should return products when category filter`() {

        `when`(repository.findByCategoryIn(productCategoryFilter, pageable)).thenReturn(listOf(productEntity))
        `when`(productMapper.toProduct(productEntity)).thenReturn(product)

        val results = productsRepositoryImpl.findAllProducts(PAGE, SIZE, SortBy.SKU, Order.ASC, productCategoryFilter)

        verify(repository, times(1)).findByCategoryIn(productCategoryFilter, pageable)
        verify(repository, never()).findAll(pageable)

        assertNotNull(results)
        assertEquals(1, results.size)
        assertEquals(product.sku, results[0].sku)
        assertEquals(product.price, results[0].price)
        assertEquals(product.description, results[0].description)
        assertEquals(product.category, results[0].category)
    }

    @Test
    fun `should return no products when empty db and category filter`() {

        `when`(repository.findByCategoryIn(productCategoryFilter, pageable)).thenReturn(listOf())

        val results = productsRepositoryImpl.findAllProducts(PAGE, SIZE, SortBy.SKU, Order.ASC, productCategoryFilter)

        verify(repository, times(1)).findByCategoryIn(productCategoryFilter, pageable)
        verify(repository, never()).findAll(pageable)

        assertNotNull(results)
        assertEquals(0, results.size)
    }

    @Test
    fun `should return all products when no category filter is null`() {

        val page: Page<ProductEntity> = PageImpl(listOf(productEntity, productEntity2), pageable, 2L)

        `when`(repository.findAll(pageable)).thenReturn(page)
        `when`(productMapper.toProduct(productEntity)).thenReturn(product)
        `when`(productMapper.toProduct(productEntity2)).thenReturn(product2)

        val results = productsRepositoryImpl.findAllProducts(PAGE, SIZE, SortBy.SKU, Order.ASC, null)

        verify(repository, times(1)).findAll(pageable)
        verify(repository, never()).findByCategoryIn(productCategoryFilter, pageable)

        assertNotNull(results)
        assertEquals(2, results.size)
        assertEquals(product.sku, results[0].sku)
        assertEquals(product.price, results[0].price)
        assertEquals(product.description, results[0].description)
        assertEquals(product.category, results[0].category)

        assertEquals(product2.sku, results[1].sku)
        assertEquals(product2.price, results[1].price)
        assertEquals(product2.description, results[1].description)
        assertEquals(product2.category, results[1].category)
    }

    @Test
    fun `should return all products when no category filter is empty`() {

        val page: Page<ProductEntity> = PageImpl(listOf(productEntity, productEntity2), pageable, 2L)

        `when`(repository.findAll(pageable)).thenReturn(page)
        `when`(productMapper.toProduct(productEntity)).thenReturn(product)
        `when`(productMapper.toProduct(productEntity2)).thenReturn(product2)

        val results = productsRepositoryImpl.findAllProducts(PAGE, SIZE, SortBy.SKU, Order.ASC, listOf())

        verify(repository, times(1)).findAll(pageable)
        verify(repository, never()).findByCategoryIn(productCategoryFilter, pageable)

        assertNotNull(results)
        assertEquals(2, results.size)
        assertEquals(product.sku, results[0].sku)
        assertEquals(product.price, results[0].price)
        assertEquals(product.description, results[0].description)
        assertEquals(product.category, results[0].category)

        assertEquals(product2.sku, results[1].sku)
        assertEquals(product2.price, results[1].price)
        assertEquals(product2.description, results[1].description)
        assertEquals(product2.category, results[1].category)
    }

    @Test
    fun `should return no products when empty db and no category filter`() {

        val page: Page<ProductEntity> = PageImpl(listOf(), pageable, 0L)

        `when`(repository.findAll(pageable)).thenReturn(page)

        val results = productsRepositoryImpl.findAllProducts(PAGE, SIZE, SortBy.SKU, Order.ASC, null)

        verify(repository, times(1)).findAll(pageable)
        verify(repository, never()).findByCategoryIn(productCategoryFilter, pageable)

        assertNotNull(results)
        assertEquals(0, results.size)
    }
}
