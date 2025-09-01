package com.example.matulegulnaz.presentation.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.key
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.data.search.SearchRepositoryImpl
import com.example.matulegulnaz.domain.product.SneakerInfo
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.domain.search.SearchInfo
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepositoryImpl: SearchRepositoryImpl
) : ViewModel() {

    private val _searchedState = MutableStateFlow<FetchResultUiState<List<SneakerInfo>>>(
        FetchResultUiState.Initial())
    val searchedState: StateFlow<FetchResultUiState<List<SneakerInfo>>> = _searchedState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addSearch(input: String) = viewModelScope.launch {
        searchRepositoryImpl.insertSearch(
            SearchInfo(text = input, time = LocalDateTime.now().toKotlinLocalDateTime())
        )
    }

    val searchList = searchRepositoryImpl.getSearch().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun searchByKeyword(keyWord: String) = viewModelScope.launch {
        _searchedState.value = FetchResultUiState.Loading()
        val result = searchRepositoryImpl.searchByKeyWord(keyWord)
        _searchedState.value = result.map(FetchResultMapper())
    }
}