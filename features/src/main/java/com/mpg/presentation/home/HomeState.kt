package com.mpg.presentation.home


data class HomeState(
    val productTicker: String = "",
    val isLoading: Boolean = false,
)