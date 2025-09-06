package com.example.matulegulnaz.domain.notification

import com.example.matulegulnaz.domain.result.FetchResult

interface NotificationRepository {
    suspend fun updateNotifStatus(notifId: Int): FetchResult<Int>
    suspend fun fetchNotification(): FetchResult<List<NotificationInfo>>

    suspend fun fetchNotificationStatus(): FetchResult<List<Boolean>>
}