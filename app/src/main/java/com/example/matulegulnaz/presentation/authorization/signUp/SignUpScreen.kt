package com.example.matulegulnaz.presentation.authorization.signUp

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.presentation.authorization.common.ui.BottomRegistrTextNav
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.presentation.authorization.common.ui.RegistrTitle
import com.example.matulegulnaz.ui.theme.poppinsFamily

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onLogIn: () -> Unit,
    onSignUp: () -> Unit,
    authViewmodel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val authState by authViewmodel.authState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val authUiResultState by authViewmodel.authUiResultState.collectAsState()

    BackHandler { onBackClick() }

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ){ padding ->
        authUiResultState.Display(
            onSuccess = {onSignUp()},
            onChangeButtonState = {},
            snackbarHostState = snackbarHostState
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 20.dp)
            ) {

                CustomTopAppBar(
                    onBack = { onBackClick() }
                )

                RegistrTitle(
                    "Register Account",
                    "Fill your details or continue with social media"
                )

                Spacer(modifier = modifier.height(30.dp))

                CustomField(
                    textValue = name,
                    onValueChanged = { name = it },
                    "xxxxxxxx",
                    isError = !authState.isNameValid,
                    label = "Your name",
                    errorText = authViewmodel.nameErrorHint
                )

                Spacer(modifier = modifier.height(12.dp))

                CustomField(
                    textValue = email,
                    onValueChanged = { email = it },
                    "xyz@gmail.com",
                    label = "Email address",
                    isError = !authState.isEmailValid,
                    errorText = authViewmodel.emailErrorHint
                )

                Spacer(modifier = modifier.height(12.dp))

                CustomField(
                    textValue = password,
                    onValueChanged = { password = it },
                    isPasswordField = true,
                    placeholder = "•••••••",
                    isError = !authState.isPasswordValid,
                    label = "Password",
                    errorText = authViewmodel.passwordErrorHint
                )

                NavigationButton(
                    text = "Sign up",
                    onAction = {
                        authViewmodel.signUp(
                            name.toString(),
                            email.toString(),
                            password.toString()
                        )
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                )

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = authViewmodel.signingError,
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = Color.Red,
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = modifier.weight(1f))

                BottomRegistrTextNav(
                    "Already have an account?",
                    "Log in",
                    onNavigateTo = { onLogIn() }
                )

            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen({}, {}, {})
}