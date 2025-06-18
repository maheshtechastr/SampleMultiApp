package com.mpg.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpg.domain.usecases.GetSavedProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSavedProductsUseCase: GetSavedProducts
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        getSavedProductsUseCase().onEach {
            _state.value = _state.value.copy(products = it)
        }.launchIn(viewModelScope)
    }
}