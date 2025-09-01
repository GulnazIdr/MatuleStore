package com.example.matulegulnaz.presentation.authorization.common

data class AuthState (
    val isNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isPolicyAccepted: Boolean = true
)