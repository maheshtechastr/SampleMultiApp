package com.mpg.data.local

import app.cash.turbine.test
import com.mpg.data.dto.ProductData
import com.mpg.data.repository.ProductDataSource
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class ProductDataSourceTest {

    private val productDao: ProductDao = mockk(relaxed = true)
    private lateinit var dataSource: ProductDataSource

    @Before
    fun setup() {
        dataSource = ProductDataSourceImpl(productDao)
    }

    @Test
    fun `upsertProduct delegates to productDao`() = runTest {
        val product = ProductData(id = 1, title = "Test", price = 10.0,
            description = "description",
            thumbnail = "thumbnail")
        coEvery { productDao.upsertProduct(product) } just Runs

        dataSource.upsertProduct(product)

        coVerify { productDao.upsertProduct(product) }
    }

    @Test
    fun `deleteProduct delegates to productDao`() = runTest {
        val product = ProductData(
            id = 2, title = "To Delete", price = 20.0,
            description = "description",
            thumbnail = "thumbnail"
        )
        coEvery { productDao.deleteProduct(product) } returns product.id

        assertEquals(dataSource.deleteProduct(product), product.id)

        coVerify { productDao.deleteProduct(product) }
    }

    @Test
    fun `getProduct delegates to productDao and returns expected value`() = runTest {
        val product = ProductData(id = 3, title = "Single", price = 15.0,
            description = "description",
            thumbnail = "thumbnail")
        coEvery { productDao.getProduct(3) } returns product

        val result = dataSource.getProduct(3)

        assertEquals(product, result)
    }

    @Test
    fun `getProducts emits list of products from DAO flow`() = runTest {
        val expected = listOf(ProductData(id = 1, title = "Flow Item", price = 30.0,
            description = "description",
            thumbnail = "thumbnail"),
            ProductData(id = 2, title = "Flow Item2", price = 20.0,
                description = "description2",
                thumbnail = "thumbnail2"))
        every { productDao.getProducts() } returns flowOf(expected)

        dataSource.getProducts().test {
            val emitted = awaitItem()
            assertEquals(expected, emitted)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
