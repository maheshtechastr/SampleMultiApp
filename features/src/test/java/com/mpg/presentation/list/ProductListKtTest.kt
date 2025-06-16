package com.mpg.presentation.list

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.mpg.presentation.util.ResultState
import org.junit.Assert.assertEquals
import org.junit.Test

class EvaluatePagingStateTest {

    @Test
    fun `returns Loading when refresh is loading`() {
        val state = CombinedLoadStates(
            refresh = LoadState.Loading,
            prepend = LoadState.NotLoading(false),
            append = LoadState.NotLoading(false),
            source = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
            ),
            mediator = null
        )

        assertEquals(ResultState.Loading, evaluatePagingState(state))
    }

    @Test
    fun `returns Error when prepend fails`() {
        val error = Throwable("prepend failed")
        val state = CombinedLoadStates(
            refresh = LoadState.NotLoading(false),
            prepend = LoadState.Error(error),
            append = LoadState.NotLoading(false),
            source = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
            ),
            mediator = null
        )
        assertEquals(ResultState.Error(LoadState.Error(error)), evaluatePagingState(state))
    }
    @Test
    fun `returns Error when append fails`() {
        val error = Throwable("append failed")
        val state = CombinedLoadStates(
            refresh = LoadState.NotLoading(false),
            prepend = LoadState.NotLoading(false),
            append = LoadState.Error(error),
            source = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
            ),
            mediator = null
        )
        assertEquals(ResultState.Error(LoadState.Error(error)), evaluatePagingState(state))
    }

    @Test
    fun `returns Success when all loads are not loading and no error`() {
        val state = CombinedLoadStates(
            refresh = LoadState.NotLoading(false),
            prepend = LoadState.NotLoading(false),
            append = LoadState.NotLoading(false),
            source = LoadStates(
                refresh = LoadState.Loading,
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false)
            ),
            mediator = null
        )
        assertEquals(ResultState.Success, evaluatePagingState(state))
    }
}
