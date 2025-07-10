package com.mpg.presentation.util

sealed class UIComponent {
    data class Toast(val message: String): UIComponent()
}