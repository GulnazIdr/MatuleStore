package com.example.matulegulnaz.presentation.authorization.signIn

import android.util.Log
import kotlin.random.Random

class OtpRandomizer {
    private var currentOtp : String? = null

    fun generateNewOtp(){
        val randomNumber =  List(4){ Random.nextInt(0, 9) }
        currentOtp = randomNumber.joinToString("")
        Log.d("OTP3 CODE", currentOtp.toString())
    }

    fun getCurrentOtp(): String? = currentOtp
}