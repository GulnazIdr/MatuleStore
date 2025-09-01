package com.example.matulegulnaz.presentation.authorization.signIn

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.authorization.common.ui.RegistrTitle
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.ForgotPasswordViewmodel
import com.example.matulegulnaz.ui.theme.drawerBackground
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.ralewayFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform

@Composable
fun OTPScreen(
    email: String,
    navigateToResetPassword: () -> Unit,
    onBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    forgotPasswordViewModel: ForgotPasswordViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val focusRequesterList = rememberSaveable { List(6) {FocusRequester()} }

    val authUiResultState = forgotPasswordViewModel.authUiResultState.collectAsState().value
    val sentOtpResult = forgotPasswordViewModel.sentOtpResult.collectAsState().value

    var timerCount by rememberSaveable { mutableIntStateOf(30) }
    var startTimer by rememberSaveable { mutableStateOf(true)}

    var inputStateEnabled by remember { mutableStateOf(true) }
    var textColor by remember { mutableStateOf(lightGrey) }

    if (startTimer) {
        LaunchedEffect(Unit) {
            timer(30).collect { timestate ->
                timerCount = timestate
            }
        }
    }

    if(timerCount == 0){
        startTimer = false
        textColor = drawerBackground
        inputStateEnabled = false
    }else{
        textColor = lightGrey
        inputStateEnabled = true
    }

    BackHandler { onBack() }

    CommonScaffold(
        snackBarHostState
    ) {
        authUiResultState.Display(
            onSuccess = {navigateToResetPassword()},
            onChangeButtonState = {},
            snackBarHostState
        )

        sentOtpResult.Display(
            onSuccess = {
                timerCount = 30
                startTimer = true
            },
            onChangeButtonState = {},
            snackBarHostState
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomTopAppBar(
                        onBack = { onBack() }
                    )

                    RegistrTitle(
                        registrTitle = "OTP verification",
                        registrDescription = "Please check your email to see the verification code"
                    )
                }

                Text(
                    text = "OTP code",
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 21.sp,
                    color = mainButtonColor
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(6) { index ->
                        OtpItem(
                            email = email,
                            index = index,
                            forgotPasswordViewmodel = forgotPasswordViewModel,
                            inputStateEnabled = inputStateEnabled,
                            focusRequester = focusRequesterList[index]
                        )
                    }
                }

                Spacer(modifier = modifier.height(100.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Resend code in",
                        fontFamily = ralewayFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = textColor,
                        modifier = Modifier.clickable(
                            enabled = !inputStateEnabled,
                            onClick = { forgotPasswordViewModel.resendOtp(email) }
                        )
                    )

                    AnimatedVisibility(
                        visible = inputStateEnabled,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text(
                            text = timerCount.toString(),
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.W400,
                            fontSize = 12.sp,
                            color = lightGrey
                        )
                    }
                }

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = authViewModel.signingError,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = Color.Red,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

fun timer(setDelayTime: Int): Flow<Int> =
    (setDelayTime -1 downTo 0).asFlow()
        .onEach { delay(1000L) }
        .onStart { emit(setDelayTime) }
        .conflate()
        .transform { remainingSecond: Int ->
            emit(remainingSecond)
        }

@Preview
@Composable
private fun OTPScreenPreview() {
    OTPScreen(
        email = "",
        onBack = {},
        navigateToResetPassword = {}
    )
}