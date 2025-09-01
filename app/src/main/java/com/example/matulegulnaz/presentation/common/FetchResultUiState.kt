package com.example.matulegulnaz.presentation.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

interface FetchResultUiState<T> {

    @Composable
    fun Display(
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (T?) -> Unit,
        onLoading: @Composable (T?) ->Unit,
        onChangeButtonState: (Boolean) -> Unit,
        snackbarHostState: SnackbarHostState
    )

    class Initial<T>: FetchResultUiState<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((T) -> Unit),
            onError: @Composable ((T?) -> Unit),
            onLoading: @Composable ((T?) -> Unit),
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {

        }
    }

    class Success<T>(val data: T): FetchResultUiState<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((T) -> Unit),
            onError: @Composable ((T?) -> Unit),
            onLoading: @Composable ((T?) -> Unit),
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            onChangeButtonState(true)
            onSuccess(data)
        }
    }

    class Error<T>(val data: T?): FetchResultUiState<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((T) -> Unit),
            onError: @Composable ((T?) -> Unit),
            onLoading: @Composable ((T?) -> Unit),
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            onChangeButtonState(true)
            onError(data)
        }
    }

    class Loading<T>(val data: T? = null): FetchResultUiState<T>{
        @Composable
        override fun Display(
            onSuccess: @Composable ((T) -> Unit),
            onError: @Composable ((T?) -> Unit),
            onLoading: @Composable ((T?) -> Unit),
            onChangeButtonState: (Boolean) -> Unit,
            snackbarHostState: SnackbarHostState
        ) {
            onLoading(data)
        }
    }

}