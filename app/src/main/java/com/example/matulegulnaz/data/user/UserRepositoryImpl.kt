package com.example.matulegulnaz.data.user
import android.util.Log
import com.example.matulegulnaz.data.authorization.AuthResult
import com.example.matulegulnaz.data.database.remote.dto.RemoteUserDto
import com.example.matulegulnaz.domain.result.FetchResult
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.domain.user.UserRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.put
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): UserRepository, Mapper() {
    override suspend fun setCurrentUserMetaData(user: User): AuthResult {
        val currentUser = supabaseClient.auth.currentSessionOrNull()
        if(currentUser != null) {
            return try {
                supabaseClient.auth.updateUser {
                    data{
                        put("surname", user.surname)
                        put("address", user.address)
                        put("phone", user.phone)
                        put("img", user.img)
                    }
                }
                // TODO: get metadata is empty in profileeditscreen 
                Log.d("METADATA OO2", user.surname.toString())
                AuthResult.Success
            } catch (e: Exception) {
                Log.e("SET METADATA", "failed: ${e::class.simpleName} ${e.message}")
                AuthResult.Error(statusCode = 503)
            }
        }else
            return AuthResult.Error(statusCode = 404)
    }

    override fun getCurrentUserData(): FetchResult<User> {
        val user = supabaseClient.auth.currentUserOrNull() ?:
        return FetchResult.Error(null)

        val json = Json { ignoreUnknownKeys = true }
        val metadata = user.userMetadata.let {
            json.decodeFromString<RemoteUserDto>(it.toString())
        }.toUser()

        Log.d("METADATA OO", metadata.surname.toString())

        return FetchResult.Success(User(
            name = metadata.name,
            img =  metadata.img,
            surname = metadata.surname,
            address = metadata.address,
            phone = metadata.phone,
            password = "secret",
            email = user.email.toString()
        ))
    }

    override suspend fun updateCurrentUserData(user: User): AuthResult {
        val currentUser = supabaseClient.auth.currentSessionOrNull()?.user
        if(currentUser != null) {
            return try {
                supabaseClient.auth.updateUser {
                    email = user.email
                    password = user.password
                    data {
                        put("name", user.name)
                    }
                }

                return AuthResult.Success

            } catch (e: Exception) {
                Log.d("USER UPDATE", "${e::message.name} ${e::class.simpleName}")
                return AuthResult.Error()
            }
        }else{
            return AuthResult.Error(statusCode = 404)
        }
    }
}