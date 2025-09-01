package com.example.matulegulnaz.presentation.authorization.signIn

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
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
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.ui.theme.poppinsFamily
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ResetPasswordScreen(
    navigateToMain: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    forgotPasswordViewModel: ForgotPasswordViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    var password by remember { mutableStateOf("") }
    val generatedPassword = forgotPasswordViewModel.generatePassword(password)
    val clipData = ClipData.newPlainText("Copied", generatedPassword)
    clipData.apply {
        description.extras = PersistableBundle().apply {
            putBoolean(ClipDescription.EXTRA_IS_SENSITIVE, true)
        }
    }
    val clipEntry = ClipEntry(clipData)
    val clipboardManager = LocalClipboard.current

    val snackbarHostState = remember { SnackbarHostState() }
    val authUiResultState by authViewModel.authUiResultState.collectAsState()

    val scope = rememberCoroutineScope()

    CommonScaffold(
        snackbarHostState = snackbarHostState
    ) {
        authUiResultState.Display(
            onSuccess = {navigateToMain()},
            onChangeButtonState = {},
            snackbarHostState
        )
        Box(
            modifier = modifier.fillMaxSize().background(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(horizontal = 20.dp)
            ) {

                CustomTopAppBar(
                    onBack = {}
                )

                RegistrTitle(
                    registrTitle = "Reset Password",
                    registrDescription = "Enter your new password"
                )

                CustomField(
                    textValue = password,
                    onValueChanged = { password = it },
                    placeholder = "xxxxxxxx",
                    label = "",
                    isError = false,
                    enabled = true,
                    errorText = "",
                    isPasswordField = true
                )

                Spacer(modifier = modifier.height(40.dp))

                Row {
                    NavigationButton(
                        "Generate password",
                        onAction = { forgotPasswordViewModel.generatePassword(password) },
                        height = 40.dp,
                        modifier = modifier.weight(1f)
                    )

                    Spacer(modifier = modifier.width(30.dp))

                    NavigationButton(
                        "Copy",
                        onAction = {
                            scope.launch {
                                clipboardManager.setClipEntry(clipEntry)
                            }
                            password = ""
                        },
                        height = 40.dp,
                        modifier = modifier.weight(1f)
                    )
                }

                Spacer(modifier = modifier.height(40.dp))

                NavigationButton(
                    "Save",
                    onAction = {
                        authViewModel.updateUserPassword(password)
                    },
                    modifier = modifier.fillMaxWidth()
                )

                Spacer(modifier = modifier.height(40.dp))


                Text(
                    text = authViewModel.passwordErrorHint,
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview
@Composable
private fun ResetPasswordScreenPreview() {
    ResetPasswordScreen(
        navigateToMain = {}
    )
}