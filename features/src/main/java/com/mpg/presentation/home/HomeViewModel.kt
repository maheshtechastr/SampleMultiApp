package com.mpg.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mpg.domain.usecases.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getProduct: GetProduct) : ViewModel() {
    val product = getProduct(
        page = 1,
        skip = 0
    ).cachedIn(viewModelScope)

}