package com.mpg.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.mpg.models.product.Product
import com.mpg.presentation.Dimens.ExtraSmallPadding2
import com.mpg.presentation.Dimens.MediumPadding1
import com.mpg.presentation.home.components.ProductCard
import com.mpg.presentation.util.ResultState

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<Product>,
    onClick: (Product) -> Unit
) {

    val handlePagingResult = handlePagingResult(products)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = products.itemCount,
            ) {
                products[it]?.let { product ->
                    ProductCard(product = product, onClick = { onClick(product) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(products: LazyPagingItems<Product>): Boolean {
    return when (val state = evaluatePagingState(products.loadState)) {
        ResultState.Loading -> {
            ShimmerEffect()
            false
        }
        is ResultState.Error -> {
            EmptyScreen(error = state.throwable)
            false
        }
        ResultState.Success -> true
    }
}

fun evaluatePagingState(loadState: CombinedLoadStates): ResultState {
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> ResultState.Loading
        error != null -> ResultState.Error(error)
        else -> ResultState.Success
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10) {
            ProductCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}