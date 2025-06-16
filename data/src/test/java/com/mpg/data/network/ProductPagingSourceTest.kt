package com.mpg.data.network

import androidx.paging.PagingSource
import com.mpg.data.dto.ProductData
import com.mpg.data.dto.ProductResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class ProductPagingSourceTest {

    private val productApi = mock(ProductApi::class.java)
    private lateinit var pagingSource: ProductPagingSource

    @Before
    fun setup() {
        pagingSource = ProductPagingSource(productApi)
    }

    @Test
    fun `load returns Page on successful load`() = runTest {
        val fakeProducts = List(10) { index ->
            ProductData(
                id = index, title = "Product $index", price = 100.0+index,
                description = "description$index",
                thumbnail = "thumbnail$index"
            )
        }
        val response = ProductResponse(
            products = fakeProducts,
            total = 100,
            skip = 0,
            limit = 10
        )

        `when`(productApi.getProducts(page = 10, skip = 10)).thenReturn(response)

        val expected = PagingSource.LoadResult.Page(
            data = fakeProducts,
            prevKey = null,
            nextKey = 2
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertEquals(expected, result)
    }

    @Test
    fun `load returns Page on successful load with zero data`() = runTest {
        val fakeProducts = emptyList<ProductData>()
        val response = ProductResponse(
            products = fakeProducts,
            total = 10,
            skip = 0,
            limit = 10
        )

        `when`(productApi.getProducts(page = 1, skip = 10)).thenReturn(response)

        val expected = PagingSource.LoadResult.Page(
            data = fakeProducts,
            prevKey = null,
            nextKey = 2
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertEquals(expected, result)
    }

    @Test
    fun `load returns Error on failure`() = runTest {
        val exception = RuntimeException("Network error")

        `when`(productApi.getProducts(page = 10, skip = 10)).thenThrow(exception)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals(exception, (result as PagingSource.LoadResult.Error).throwable)
    }
}
