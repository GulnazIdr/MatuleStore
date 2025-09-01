package com.example.matulegulnaz.presentation.authorization.common.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matulegulnaz.data.authorization.AccountService
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.presentation.authorization.common.AuthResultMapper
import com.example.matulegulnaz.presentation.authorization.common.AuthState
import com.example.matulegulnaz.presentation.authorization.common.AuthUiResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountService: AccountService,
    private val mapper: AuthResultMapper
) : ViewModel() {

    private val _authUiResultState = MutableStateFlow<AuthUiResultState>(AuthUiResultState.Initial)
    val authUiResultState: StateFlow<AuthUiResultState> get() = _authUiResultState.asStateFlow()

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> get() = _authState.asStateFlow()

    var nameErrorHint by mutableStateOf("")
    var emailErrorHint by mutableStateOf("")
    var passwordErrorHint by mutableStateOf("")
    var signingError by mutableStateOf("")

    fun signUp(
        name: String?,
        email: String?,
        password: String?
    ){
        validateFields(
            name,email,password
        )

        with(_authState.value){
            if(isNameValid && isEmailValid && isPasswordValid){
                viewModelScope.launch {

                    _authUiResultState.value = AuthUiResultState.Loading("Loading..")

                    val result = accountService.signUp(
                        User(
                            name = name.toString(),
                            password = password.toString(),
                            email = email.toString()
                        )
                    )

                    _authUiResultState.value = result.map(mapper)
                }
            }
        }
    }

    fun signIn(
        email: String?,
        password: String?
    ) = viewModelScope.launch {
            _authUiResultState.value = AuthUiResultState.Loading("Verifying your data..")
            val result = accountService.signIn(User(email = email.toString(), password = password.toString()))
            _authUiResultState.value = result.map(mapper)
    }

    fun updateUserPassword(newPassword: String){
        val result = validatePassword(newPassword)
        if(result){
            _authUiResultState.value = AuthUiResultState.Loading("Saving..")
            viewModelScope.launch {
                val res = accountService.updatePassword(newPassword)
                _authUiResultState.value = res.map(mapper)
            }
        }
    }

    fun validateFields(
        name: String? = "",
        email: String? = "",
        password: String? = ""
    ){
        val validName = name.toString().isNotBlank()
        val validEmail = Regex("^[a-z0-9._]+@[a-z]+\\.[a-z]{2,}\$").matches(email.toString())

        if(!validEmail) emailErrorHint = "Incorrect form of email"
        if(!validName) nameErrorHint = "This field is necessary"

        _authState.update { state ->
            state.copy(
                isNameValid = validName,
                isEmailValid = validEmail,
                isPasswordValid = validatePassword(password.toString())
            )
        }
    }

    fun signOut() = viewModelScope.launch {
        accountService.signOut()
    }


    fun validatePassword(password: String): Boolean{
        val errorList = listOf<String>(
            "Password must have at least 8 characters",
            "Password must have lowercase letters and digits",
            "Password must have uppercase letter",
            "Password must have symbols"
        )

        val noLetterDigit = !password.any{it.isLetterOrDigit()}
        val noUpper = !password.any{it.isUpperCase()}
        val notEnoughChars = password.length < 8
        val passwordIsBlank = password.isBlank()

        val regexSymbol = Regex(".*[!@#$%].*")
        val noSymbol = !password.matches(regexSymbol)

        passwordErrorHint = when{
            passwordIsBlank -> errorList[0]
            noLetterDigit -> errorList[1]
            noUpper -> errorList[2]
            noSymbol -> errorList[3]
            notEnoughChars -> errorList[0]
            else -> ""
        }

        if(passwordIsBlank || notEnoughChars || noUpper || noLetterDigit || noSymbol)
            return false

        return true
    }
}