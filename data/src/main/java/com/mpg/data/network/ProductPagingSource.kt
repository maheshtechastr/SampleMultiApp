package com.mpg.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mpg.data.dto.ProductData
import jakarta.inject.Inject

class ProductPagingSource @Inject constructor(
    private val productApi: ProductApi,
) : PagingSource<Int, ProductData>() {

    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalProductCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {
        val page = params.key ?: 1
        return try {
            val productResponse = productApi.getProducts(page = 10, skip = page * 10)
            totalProductCount += productResponse.products.size
//            val articles = productResponse.products.distinctBy { it.title } //Remove duplicates

            LoadResult.Page(
                data = productResponse.products,
                nextKey = if (totalProductCount == productResponse.total) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}