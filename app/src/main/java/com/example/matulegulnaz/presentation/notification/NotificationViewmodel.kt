package com.example.matulegulnaz.presentation.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.domain.notification.NotificationInfo
import com.example.matulegulnaz.domain.notification.NotificationRepository
import com.example.matulegulnaz.presentation.common.FetchResultMapper
import com.example.matulegulnaz.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewmodel @Inject constructor(
    val notificationRepository: NotificationRepository
): ViewModel() {

    private val _fetchContentRes = MutableStateFlow<
            FetchResultUiState<List<NotificationInfo>>>(FetchResultUiState.Initial())
    val fetchContentRes: StateFlow<FetchResultUiState<List<NotificationInfo>>> =
        _fetchContentRes.asStateFlow()

    private val _updateNotificationState = MutableStateFlow<
            FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val updateNotificationState: StateFlow<FetchResultUiState<Int>> =
        _updateNotificationState.asStateFlow()

    private val _fetchNotifStatus = MutableStateFlow<
            FetchResultUiState<List<Boolean>>>(FetchResultUiState.Initial())
    val fetchNotifStatus: StateFlow<FetchResultUiState<List<Boolean>>> = _fetchNotifStatus.asStateFlow()

    fun fetchNotificationContent() = viewModelScope.launch {
        val res = notificationRepository.fetchNotification()
        _fetchContentRes.value = res.map(FetchResultMapper())
    }

    fun updateNotifStatus(notifId: Int) = viewModelScope.launch {
        val res = notificationRepository.updateNotifStatus(notifId)
        _updateNotificationState.value = res.map(FetchResultMapper())
    }

    fun fetchNotifStatus() = viewModelScope.launch {
        val res = notificationRepository.fetchNotificationStatus()
        _fetchNotifStatus.value = res.map(FetchResultMapper())
    }
}