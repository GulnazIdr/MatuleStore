package com.example.matulegulnaz.data.authorization

import android.util.Log
import com.example.matulegulnaz.domain.user.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
): AccountService {

    override suspend fun signUp(
        user: User
    ): AuthResult {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                email = user.email
                password = user.password
                data = buildJsonObject{
                    put("name", user.name)
                    put("surname", user.surname)
                    put("address", user.address)
                    put("phone", user.phone)
                    put("img", user.img)
                }
            }

            signIn(user)
            AuthResult.Success
        }catch (e: AuthRestException){
            AuthResult.Error(statusCode = 401)
        } catch (e: Exception) {
            Log.e("SIGN UP", "failed: ${e::class.simpleName} ${e.message}")
            AuthResult.Error(statusCode = 503)
        }
    }

    override suspend fun signIn(user: User): AuthResult {
        return try{
            supabaseClient.auth.signInWith(Email){
                email = user.email
                password = user.password
            }
            AuthResult.Success
        } catch (e: AuthRestException){
            AuthResult.Error(statusCode = 400)
        }catch (e: Exception){
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error(statusCode = 503)
        }
    }

    override suspend fun signOut() {
        supabaseClient.auth.signOut()
    }

    override suspend fun deleteAccount() {
       // Firebase.auth.
    }

    override suspend fun updatePassword(newPassword: String): AuthResult {
       return try {
           supabaseClient.auth.updateUser {
               password = newPassword
           }
           AuthResult.Success
       }catch (e: Exception){
           Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
           AuthResult.Error(statusCode = 503)
       }
    }

    override suspend fun sendOtp(email: String): AuthResult {
        return try {
            supabaseClient.auth.resetPasswordForEmail(email = email)
            AuthResult.Success
        } catch (e: AuthRestException){
            AuthResult.Error(statusCode = 506)
        } catch (e: Exception){
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error(statusCode = 503)
        }
    }

    override suspend fun checkOtp(
        email: String,
        token: String
    ): AuthResult {
        return try {
            supabaseClient.auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = email,
                token = token
            )
            AuthResult.Success
        }catch (e: Exception){
            Log.e("LOGIN", "Something else went wrong: ${e::class.simpleName} ${e.message}")
            AuthResult.Error(statusCode = 503)
        }
    }
}