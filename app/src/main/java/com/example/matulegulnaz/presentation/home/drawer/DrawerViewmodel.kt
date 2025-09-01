package com.example.matulegulnaz.presentation.home.drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.domain.user.UserRepository
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class DrawerViewmodel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _drawerState = MutableStateFlow<DrawerState>(DrawerState.Collapsed)
    val drawerState: StateFlow<DrawerState> = _drawerState.asStateFlow()

    private val _fetchUserDataState = MutableStateFlow<
            FetchResultUiState<User>>(FetchResultUiState.Initial())
    val fetchUserDataState: StateFlow<FetchResultUiState<User>> = _fetchUserDataState
        .onStart { getCurrentUserData() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = FetchResultUiState.Initial()
            )

    fun changeDrawerState(){
        _drawerState.value =
            if(_drawerState.value is DrawerState.Collapsed) DrawerState.Expanded
            else DrawerState.Collapsed
    }

    fun getCurrentUserData(){
        _fetchUserDataState.value = FetchResultUiState.Loading()
        _fetchUserDataState.value = userRepository.getCurrentUserData().map(FetchResultMapper())
    }
}