package com.example.matulegulnaz.presentation.cart.common

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.domain.order.OrderInfo
import com.example.matulegulnaz.domain.order.OrderWithProductInfo
import com.example.matulegulnaz.domain.order.OrdersWithProducts
import com.example.matulegulnaz.domain.product.SneakerRepository
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.domain.result.FetchResult.Success
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.domain.user.UserRepository
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    private val _isModalOpen = mutableStateOf(false)
    val isModalOpen: State<Boolean> = _isModalOpen

    private val _fetchUserDataState = MutableStateFlow<
            FetchResultUiState<User>>(FetchResultUiState.Initial())
    val fetchUserDataState: StateFlow<FetchResultUiState<User>> = _fetchUserDataState
        .onStart { getCurrentUserData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Initial()
        )

    private val _makeOrderState = MutableStateFlow<List<
            FetchResultUiState<Int>>>(listOf(FetchResultUiState.Initial()))
    val makeOrderState: StateFlow<List<FetchResultUiState<Int>>> = _makeOrderState.asStateFlow()

    private val _fetchOrderContentState = MutableStateFlow<
            FetchResultUiState<List<OrderWithProductInfo>>>(FetchResultUiState.Initial())
    val fetchOrderContentState: StateFlow<FetchResultUiState<List<OrderWithProductInfo>>> =
        _fetchOrderContentState
        .onStart { fetchOrderContent() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Initial()
        )

    private val _fetchOrderById = MutableStateFlow<
            FetchResultUiState<OrderWithProductInfo>>(FetchResultUiState.Initial())
    val fetchOrderById: StateFlow<FetchResultUiState<OrderWithProductInfo>> =
        _fetchOrderById.asStateFlow()

    fun getCurrentUserData(){
        _fetchUserDataState.value = FetchResultUiState.Loading()
        _fetchUserDataState.value = userRepository.getCurrentUserData().map(FetchResultMapper())
    }

    fun makeOrder(order: List<OrderInfo>) = viewModelScope.launch {
        val res = order.map { sneakerRepository.addToOrderList(it) }
        _makeOrderState.value = res.map{ it.map(FetchResultMapper()) }
        if(_makeOrderState.value.any { it is FetchResultUiState.Success<Int> }) {
            _isModalOpen.value = true
            order.map { sneakerRepository.deleteFromCart(it.sneakerId) }
        }
    }

    fun fetchOrderContent() = viewModelScope.launch {
        _fetchOrderContentState.value = FetchResultUiState.Loading()
        val res = sneakerRepository.fetchOrderContent()
        _fetchOrderContentState.value = res.map(FetchResultMapper())
    }

    fun getOrderById(id: Int) = viewModelScope.launch {
        _fetchOrderById.value = sneakerRepository.getOrderById(id).map(FetchResultMapper())
    }
}