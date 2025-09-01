package com.example.matulegulnaz.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.data.database.local.CategoryFetchContent
import com.example.matulegulnaz.data.product.SneakerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SneakerViewModel @Inject constructor(
    private val sneakerRepository: SneakerRepositoryImpl
): ViewModel() {

    private val _fetchResultState = MutableStateFlow<
            FetchResultUiState<CategoryFetchContent>>(FetchResultUiState.Initial())
    val fetchResultState: StateFlow<FetchResultUiState<CategoryFetchContent>> =
        _fetchResultState.onStart { fetchSneaker() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = FetchResultUiState.Loading()
            )

    private val _favoriteResultState =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val favoriteResultState: StateFlow<FetchResultUiState<Int>>
        get () = _favoriteResultState.asStateFlow()

    private val _changeAmountState =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val changeAmountState: StateFlow<FetchResultUiState<Int>> =
        _changeAmountState.asStateFlow()

    private val _deleteResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val deleteResult: StateFlow<FetchResultUiState<Int>> =
        _deleteResult.asStateFlow()

    private val _addToCartResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val addToCartResult: StateFlow<FetchResultUiState<Int>> =
        _addToCartResult.asStateFlow()

    fun fetchSneaker(){
        viewModelScope.launch {
            _fetchResultState.value = FetchResultUiState.Loading()
            val result = sneakerRepository.fetchContent()
            _fetchResultState.value = result.map(FetchResultMapper())
        }
    }

    fun changeFavoriteState(id: Int) =  viewModelScope.launch {
        val result = sneakerRepository.changeFavoriteState(id)
        _favoriteResultState.value = result.map(FetchResultMapper())
    }

    fun changeAmount(sneakerId: Int, amount: Int){
        viewModelScope.launch {
            val result = sneakerRepository.changeAmount(sneakerId, amount)
            _changeAmountState.value = result.map(FetchResultMapper())
        }
    }

    fun deleteFromCart(sneakerId: Int){
        viewModelScope.launch {
            val res = sneakerRepository.deleteFromCart(sneakerId)
            _deleteResult.value = res.map(FetchResultMapper())
        }
    }

    fun addToCart(sneakerId: Int) = viewModelScope.launch {
        val res = sneakerRepository.addToCart(sneakerId)
        _addToCartResult.value = res.map(FetchResultMapper())
    }


}