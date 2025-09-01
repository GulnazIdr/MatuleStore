package com.example.matulegulnaz.presentation.authorization.signIn

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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.authorization.common.ui.BottomRegistrTextNav
import com.example.matulegulnaz.presentation.authorization.common.ui.RegistrTitle
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.poppinsFamily

@Composable
fun SignInScreen(
    onRecoveryClick: () -> Unit,
    onSignIn: () -> Unit,
    onCreateAccount: () -> Unit,
    authViewmodel: AuthViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val authUiResultState by authViewmodel.authUiResultState.collectAsState()

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) { padding ->
        authUiResultState.Display(
            onSuccess = {onSignIn()},
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
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CustomTopAppBar(
                    onBack = {},
                    modifier = modifier.align(Alignment.Start)
                )

                RegistrTitle(
                    "Hello Again!",
                    "Fill your details or continue with social media"
                )

                CustomField(
                    textValue = email,
                    onValueChanged = { email = it },
                    placeholder = "xyz@gmail.com",
                    isError = false,
                    label = "Email address",
                    enabled = true,
                    contentTypeModifier = Modifier.semantics{contentType = ContentType.EmailAddress }
                )

                Spacer(modifier = modifier.height(30.dp))

                CustomField(
                    textValue = password,
                    onValueChanged = { password = it },
                    placeholder = "•••••••",
                    isError = false,
                    isPasswordField = true,
                    label = "Password",
                    enabled = true,
                    contentTypeModifier = Modifier.semantics{contentType = ContentType.Password }
                )

                TextButton(
                    onClick = { onRecoveryClick() },
                    modifier = modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Recovery password",
                        color = lightGrey,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        fontFamily = poppinsFamily,
                        lineHeight = 16.sp,
                    )
                }

                NavigationButton(
                    "Sign in",
                    onAction = {
                        authViewmodel.signIn(email, password)
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
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
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.weight(1f))

                BottomRegistrTextNav(
                    "New User?",
                    "Create account",
                    onNavigateTo = { onCreateAccount() }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignInScreenPreview() {
    SignInScreen({}, {}, {})
}