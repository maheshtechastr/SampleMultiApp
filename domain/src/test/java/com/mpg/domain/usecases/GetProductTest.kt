package com.mpg.domain.usecases

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.mpg.domain.repository.ProductRepository
import com.mpg.models.product.Product
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetProductTest {

    @Mock
    lateinit var productRepository: ProductRepository

    private lateinit var getProduct: GetProduct

    @Before
    fun setup() {
        productRepository = mockk<ProductRepository>()
        getProduct = GetProduct(productRepository)
    }

    @Test
    fun `invoke calls repository and emits correct PagingData`() = runTest {
        // Arrange
        val mockProduct = Product(id = 1, title = "Mock", description = "Desc", price = 999.0, thumbnail = "")
        val pagingData = PagingData.from(listOf(mockProduct))
        coEvery { productRepository.getProduct(page = 1, skip = 0) } returns flowOf(pagingData)

        // Act
        val resultFlow = getProduct(1, 0)
        val emittedItems = resultFlow.asSnapshot()

        // Assert
        assertEquals(1, emittedItems.size)
        assertEquals("Mock", emittedItems[0].title)
    }
    @Test
    fun `invoke calls repository and emits zero PagingData`() = runTest {
        // Arrange
        val pagingData = PagingData.from(emptyList<Product>())
        coEvery { productRepository.getProduct(page = 1, skip = 0) } returns flowOf(pagingData)

        // Act
        val resultFlow = getProduct(1, 0)
        val emittedItems = resultFlow.asSnapshot()

        // Assert
        assertEquals(0, emittedItems.size)
    }
}
