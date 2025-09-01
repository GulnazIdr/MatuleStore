package com.example.matulegulnaz.presentation.authorization.common.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.data.authorization.AccountService
import com.example.matulegulnaz.presentation.authorization.common.AuthResultMapper
import com.example.matulegulnaz.presentation.authorization.common.AuthUiResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

@HiltViewModel
class ForgotPasswordViewmodel @Inject constructor(
    private val accountService: AccountService,
    private val mapper: AuthResultMapper
): ViewModel() {

    private val _authUiResultState = MutableStateFlow<AuthUiResultState>(AuthUiResultState.Initial)
    val authUiResultState: StateFlow<AuthUiResultState> get() = _authUiResultState.asStateFlow()

    private val _sentOtpResult = MutableStateFlow<AuthUiResultState>(AuthUiResultState.Initial)
    val sentOtpResult: StateFlow<AuthUiResultState> get() = _sentOtpResult.asStateFlow()

    private val _modalWindowState = mutableStateOf(false)
    var modalWindowState : State<Boolean> = _modalWindowState

    val otpList = mutableStateListOf("", "", "", "", "", "")

    fun setOtpItem(email: String, item: String, index: Int){
        otpList[index] = item.toString()

        if(otpList.all { it.isNotEmpty() }) {
            checkOtp(email, otpList.joinToString(""))
        }
    }

    fun getOtpItem(index: Int): String{
        return otpList[index]
    }

    fun resendOtp(email: String){
        sendOtp(email)
    }

    fun sendOtp(email: String) = viewModelScope.launch {
        _sentOtpResult.value = AuthUiResultState.Loading("Sending..")
        val result = accountService.sendOtp(email)
        _sentOtpResult.value = result.map(mapper)
    }

    fun checkOtp(email: String, entered: String) = viewModelScope.launch {
        val result = accountService.checkOtp(email,entered)
        _authUiResultState.value = result.map(mapper)
    }

    fun showSuccessDialog(){
        _modalWindowState.value = true
    }

    fun generatePassword(password: String): String{
        _authUiResultState.value = AuthUiResultState.Loading("Generating..")
        if(password.isNotEmpty()) return password.toGeneratedPassword()
        else{
            _authUiResultState.value = AuthUiResultState.Loading("Type a text in the field" +
                    " to generate the password")
            return ""
        }
    }

    private fun String.toGeneratedPassword(): String{
        val latin = lowercase()
            .replace("а", "a")
            .replace("б", "b")
            .replace("в", "v")
            .replace("г", "g")
            .replace("д", "d")
            .replace("е", "e")
            .replace("ё", "e")
            .replace("ж", "zh")
            .replace("з", "z")
            .replace("и", "i")
            .replace("й", "j")
            .replace("к", "k")
            .replace("л", "l")
            .replace("м", "m")
            .replace("н", "n")
            .replace("о", "o")
            .replace("п", "p")
            .replace("р", "r")
            .replace("с", "s")
            .replace("т", "t")
            .replace("у", "u")
            .replace("ф", "f")
            .replace("х", "h")
            .replace("ц", "c")
            .replace("ч", "ch")
            .replace("ш", "sh")
            .replace("щ", "sch")
            .replace("ъ", "")
            .replace("ы", "y")
            .replace("ь", "")
            .replace("э", "e")
            .replace("ю", "yu")
            .replace("я", "ya")

        val modifiedPhrase = latin
            .replace("i", "1")
            .replace("o", "0")
            .replace("s", "5")
            .replace("e", "3")
            .replace("a", "4")
            .replace("t", "7")
            .replace("g", "9")
            .replace("z", "2")
            .replace(" ", "_")


        val specialChars = listOf(
            '!',
            '@',
            '#',
            '$',
            '%',
            '^',
            '&',
            '*',
            '(',
            ')',
            '-',
            '_',
            '+',
            '=',
            '[',
            ']',
            '{',
            '}',
            '|',
            ';',
            ':',
            '<',
            '>',
            '.',
            ',',
            '?',
            '/',
            '`',
            '~'
        )

        val stringBuffer = StringBuffer(modifiedPhrase)

        for (n in stringBuffer.indices step 4){
            if(n < stringBuffer.length ){
                stringBuffer.insert(n, specialChars.random())
            }
        }

        for (n in stringBuffer.indices){
            if(Random.nextBoolean() || stringBuffer.all { it.isLowerCase() } ){
                stringBuffer.replace(n, n, stringBuffer[n].uppercase())
            }
        }

        if(stringBuffer.length < 8) stringBuffer.append(specialChars.random())

        Log.d("PASSWORD CHECK", stringBuffer.toString())

        return stringBuffer.toString()
    }
}