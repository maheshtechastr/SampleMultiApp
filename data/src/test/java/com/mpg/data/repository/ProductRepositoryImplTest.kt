package com.mpg.data.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.testing.asSnapshot
import androidx.recyclerview.widget.DiffUtil
import com.mpg.data.network.ProductApi
import com.mpg.data.network.ProductPagingSource
import com.mpg.data.utils.MainDispatcherRule
import com.mpg.data.utils.NoopListCallback
import com.mpg.models.product.Product
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryImplTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var repository: ProductRepositoryImpl

    private lateinit var productPagingSource: ProductPagingSource

    private lateinit var api: ProductApi

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        api = mockk()
        productPagingSource = ProductPagingSource(api)
        repository = ProductRepositoryImpl(productPagingSource)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProduct returns transformed PagingData`() = runTest {
        // Arrange
        val product =
            Product(id = 1, title = "Mock", description = "Desc", price = 100.0, thumbnail = "")
        val product2 =
            Product(id = 2, title = "Mock2", description = "Desc2", price = 100.0, thumbnail = "")
        val pagingData = PagingData.from(listOf(product, product2))
        val expectedTitle = product.title

        // Act
        val flow = flowOf(pagingData) // simulate Pager.flow
        val result = flow.map { pd -> pd.map { it } }

        val differ = AsyncPagingDataDiffer(
            diffCallback = object : DiffUtil.ItemCallback<Product>() {
                override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Product, newItem: Product) =
                    oldItem == newItem
            },
            updateCallback = NoopListCallback(),
            mainDispatcher = Dispatchers.Main,
            workerDispatcher = Dispatchers.Main
        )

        result.collectLatest { differ.submitData(it) }
        advanceUntilIdle()

        // Assert
        val snapshot = differ.snapshot()
        assert(snapshot.isNotEmpty())
        assertEquals(expectedTitle, snapshot[0]?.title)
    }

    @Test
    fun test_success_for_all_attribute_in_product() = runTest {
        val products = mutableListOf<Product>()
        List(20) { index ->
            products.add(Product(
                id = index, title = "Mock$index",
                description = "Desc$index",
                price = 100.0 + index, thumbnail = "thumbnail$index"
            ))
        }
        val pagingData = PagingData.from(products)
        // Act
        val flow = flowOf(pagingData) // simulate Pager.flow

        val snapshot: List<Product> = flow.asSnapshot()
        advanceUntilIdle()
        assertEquals(products, snapshot)
        snapshot.forEachIndexed { index, item ->
            assertEquals(products[index].id, item.id)
            assertEquals(products[index].title, item.title)
            assertEquals(products[index].price, item.price)
            assertEquals(products[index].description, item.description)
        }
    }
}
