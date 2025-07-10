package com.mpg.presentation.details


sealed class DetailsEvent {
    object RemoveSideEffect : DetailsEvent()
}