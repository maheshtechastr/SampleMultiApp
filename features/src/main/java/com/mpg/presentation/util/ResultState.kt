package com.mpg.presentation.util

import androidx.paging.LoadState

sealed class ResultState {
    object Loading : ResultState()
    data class Error(val throwable: LoadState.Error) : ResultState()
    object Success : ResultState()
}