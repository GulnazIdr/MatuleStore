package com.example.matulegulnaz.presentation.authorization.signIn

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.authorization.common.ui.RegistrTitle
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.ForgotPasswordViewmodel
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.components.NavigationButton

@Composable
fun ForgotPasswordScreen(
    onBackClick: () -> Unit,
    navigateToOtp: (String) -> Unit,
    forgotPasswordViewModel: ForgotPasswordViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("gulnazidrisova91@gmail.com") }
    var modalWindowOpened = remember {forgotPasswordViewModel.modalWindowState}

    val snackBarHostState = remember { SnackbarHostState() }

    val sentOtpResult = forgotPasswordViewModel.sentOtpResult.collectAsState().value

    var customModifier =
        if(modalWindowOpened.value) modifier
            .fillMaxSize()
            .background(Color.White)
            .blur(radius = 4.dp)
        else modifier
            .fillMaxSize()
            .background(Color.White)

    BackHandler { onBackClick() }

    if(modalWindowOpened.value)
        ModalWindow(email = email, navigateToOtp = {navigateToOtp(email)})

    CommonScaffold(
        snackBarHostState
    ) { padding ->
        sentOtpResult.Display(
            onSuccess = {forgotPasswordViewModel.showSuccessDialog()},
            onChangeButtonState = {},
            snackBarHostState
        )

        Box(modifier = customModifier.padding(padding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 20.dp)
            ) {
                CustomTopAppBar(
                    onBack = { onBackClick() }
                )

                RegistrTitle(
                    registrTitle = "Forgot Password",
                    registrDescription = "Enter your Email account to reset your password"
                )

                CustomField(
                    textValue = email,
                    onValueChanged = { email = it },
                    placeholder = "xxxxxxxx",
                    label = "",
                    isError = false,
                    enabled = true,
                    errorText = ""
                )

                Spacer(modifier = modifier.height(40.dp))

                NavigationButton(
                    "Reset password",
                    onAction = {
                        forgotPasswordViewModel.sendOtp(email)
                    },
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordPreview() {
    ForgotPasswordScreen(
        navigateToOtp = {},
        onBackClick = {}

    )
}