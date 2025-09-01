package com.example.matulegulnaz.presentation.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.domain.user.UserRepository
import com.example.matulegulnaz.presentation.authorization.common.AuthResultMapper
import com.example.matulegulnaz.presentation.authorization.common.AuthUiResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _updateProfileResultState = MutableStateFlow<
            AuthUiResultState>(AuthUiResultState.Initial)
    val updateProfileResultState: StateFlow<AuthUiResultState> =
        _updateProfileResultState.asStateFlow()

    fun updateCurrentUserData(user: User){
        viewModelScope.launch {
            val result = userRepository.updateCurrentUserData(user)
            _updateProfileResultState.value = result.map(AuthResultMapper())
        }
    }

    fun setCurrentUserMetaData(user: User){
        viewModelScope.launch {
            val res = userRepository.setCurrentUserMetaData(user)
            _updateProfileResultState.value = res.map(AuthResultMapper())
        }
    }
}