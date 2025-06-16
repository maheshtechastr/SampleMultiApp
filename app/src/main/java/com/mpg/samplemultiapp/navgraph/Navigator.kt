package com.mpg.samplemultiapp.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.mpg.models.product.Product
import com.mpg.presentation.details.DetailsScreen
import com.mpg.presentation.details.DetailsViewModel
import com.mpg.presentation.home.HomeScreen
import com.mpg.presentation.home.HomeViewModel


@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val products = viewModel.product.collectAsLazyPagingItems()
            HomeScreen(
                products = products,
                navigateToDetails = { product ->
                    navigateToDetails(
                        navController = navController,
                        product = product
                    )
                }
            )
        }// End HomeList

        composable(Route.DetailsScreen.route) { backStackEntry ->
            val viewModel: DetailsViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
                ?.let { product ->
                    DetailsScreen(
                        product = product,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() },
                        sideEffect = viewModel.sideEffect
                    )
                }
        }//End Details

    }
}

private fun navigateToDetails(navController: NavController, product: Product) {
    navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}