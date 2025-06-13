package com.mpg.presentation.home

import androidx.lifecycle.ViewModel
import com.mpg.domain.usecases.GetProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getProduct: GetProduct) : ViewModel() {

}