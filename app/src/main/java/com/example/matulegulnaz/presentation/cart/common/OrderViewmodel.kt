package com.example.matulegulnaz.presentation.cart.common

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.domain.user.UserRepository
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewmodel @Inject constructor(
    private val userRepository: UserRepository,
    private val sneakerRepository: SneakerRepository
): ViewModel() {

    private val _fetchUserDataState = MutableStateFlow<
            FetchResultUiState<User>>(FetchResultUiState.Initial())
    val fetchUserDataState: StateFlow<FetchResultUiState<User>> = _fetchUserDataState
        .onStart { getCurrentUserData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Initial()
        )

    private val _makeOrderState = MutableStateFlow<
            FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val makeOrderState: StateFlow<FetchResultUiState<Int>> = _makeOrderState.asStateFlow()

    private val _fetchOrderContentState = MutableStateFlow<
            FetchResultUiState<List<OrderInfo>>>(FetchResultUiState.Initial())
    val fetchOrderContentState: StateFlow<FetchResultUiState<List<OrderInfo>>> =
        _fetchOrderContentState
        .onStart { fetchOrderContent() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Initial()
        )

    fun getCurrentUserData(){
        _fetchUserDataState.value = FetchResultUiState.Loading()
        _fetchUserDataState.value = userRepository.getCurrentUserData().map(FetchResultMapper())
    }

    fun makeOrder() = viewModelScope.launch {
        val res =  sneakerRepository.addToOrderList()
        _makeOrderState.value = res.map(FetchResultMapper())
    }

    fun fetchOrderContent() = viewModelScope.launch {
        _fetchOrderContentState.value = FetchResultUiState.Loading()
        val res = sneakerRepository.fetchOrderContent()
        _fetchOrderContentState.value = res.map(FetchResultMapper())
    }
}