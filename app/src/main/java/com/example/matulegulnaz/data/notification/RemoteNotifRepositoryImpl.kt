package com.example.matulegulnaz.data.notification

import android.util.Log
import com.example.matulegulnaz.data.database.local.LocalMapper
import com.example.matulegulnaz.data.database.remote.dto.RemoteNotificationDto
import com.example.matulegulnaz.domain.notification.NotificationInfo
import com.example.matulegulnaz.domain.notification.NotificationRepository
import com.example.matulegulnaz.domain.result.FetchResult
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import javax.inject.Inject

class RemoteNotifRepositoryImpl @Inject constructor(
    val supabaseClient: SupabaseClient
): NotificationRepository, LocalMapper() {

    override suspend fun updateNotifStatus(notifId: Int): FetchResult<Int> {
        return try {
            supabaseClient.from("notification").update({
                RemoteNotificationDto::isRead setTo true
            }){filter {
                RemoteNotificationDto::id eq notifId
            }}

            FetchResult.Success(notifId)

        }catch (e: Exception){
            Log.e("UPDATE NOTIFICATIONS", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun fetchNotification(): FetchResult<List<NotificationInfo>> {
        return try {
            val fetchContent = supabaseClient.from("notification")
                .select()
                .decodeList<RemoteNotificationDto>()

            val notifContent = fetchContent.map { notif ->
                notif.toNotification()
            }
            FetchResult.Success(notifContent)

        }catch (e: Exception){
            Log.e("FETCH NOTIFICATIONS", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }

    override suspend fun fetchNotificationStatus(): FetchResult<List<Boolean>> {
        val user = supabaseClient.auth.currentSessionOrNull()?.user ?:
        return FetchResult.Error(null)
        return try {
            val fetchUnreadNotifications = supabaseClient.from("notification")
                .select(columns = Columns.list("isRead")){
                    filter {
                        RemoteNotificationDto::user_id eq user.id
                        RemoteNotificationDto::isRead eq false
                    }
                }.decodeList<Map<String, Boolean>>()
                .map { it["isRead"] == false }

          //  val res = fetchUnreadNotifications.map { it.toNotification() }
            FetchResult.Success(fetchUnreadNotifications)
        }catch (e: Exception){
            Log.e("FETCH NOTIFICATIONS STATUS", "${e::class.simpleName} ${e.message}")
            FetchResult.Error(null)
        }
    }


}