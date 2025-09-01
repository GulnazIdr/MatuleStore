package com.example.matulegulnaz.presentation.home.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.matulegulnaz.R
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.components.CustomRoundedButton
import com.example.matulegulnaz.presentation.components.CustomTopAppBar
import com.example.matulegulnaz.presentation.components.NavigationButton
import com.example.matulegulnaz.presentation.home.drawer.DrawerViewmodel
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.lightGrey
import com.example.matulegulnaz.ui.theme.poppinsFamily
import com.example.matulegulnaz.ui.theme.profileGrey

@Composable
fun ProfileScreen(
    onBack:() -> Unit,
    onRecoveryClick:() -> Unit,
    navigateToProfileEdit: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    drawerViewmodel: DrawerViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val updateProfileResultState = profileViewModel.updateProfileResultState.collectAsState().value
    val fetchUserState = drawerViewmodel.fetchUserDataState.collectAsState().value

    fetchUserState.Display(
        onSuccess = { userData ->
            name = userData.name
            email = userData.email
            password = userData.password
        },
        onError = {},
        onLoading = {
            CircleLoading()
        },
        onChangeButtonState = {},
        snackbarHostState = snackbarHostState
    )

    BackHandler { onBack }

    CommonScaffold(
        snackbarHostState
    ) { padding ->

        updateProfileResultState.Display(
            onSuccess = { },
            onChangeButtonState = {},
            snackbarHostState = snackbarHostState
        )

        Column(
            modifier = modifier.padding(padding)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomTopAppBar(
                    onBack = { onBack() },
                    title = "Profile"
                )

                Spacer(modifier = Modifier.height(50.dp))

                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )

                CustomRoundedButton(
                    onAction = { navigateToProfileEdit() },
                    size = 19.dp,
                    iconSize = 6.dp,
                    painterResource = R.drawable.edit,
                    backgroundColor = bluePrimaryColor,
                    modifier = Modifier
                        .offset(17.dp, (-18).dp)
                )

                Spacer(modifier = Modifier.height(22.dp))

                CustomField(
                    textValue = name,
                    onValueChanged = { name = it },
                    placeholder = "",
                    isError = false,
                    errorText = "",
                    label = "Your name",
                    backgroundColor = profileGrey
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomField(
                    textValue = email,
                    onValueChanged = { email = it },
                    placeholder = "",
                    isError = false,
                    errorText = "",
                    label = "Email",
                    backgroundColor = profileGrey
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomField(
                    textValue = password,
                    onValueChanged = { password = it },
                    placeholder = "",
                    isError = false,
                    errorText = "",
                    label = "Password",
                    isPasswordField = true,
                    backgroundColor = profileGrey
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

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    text = "Save",
                    onAction = { profileViewModel.updateCurrentUserData(User(
                        name = name,
                        password = password,
                        email = email
                    )) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(
        onBack = {},
        onRecoveryClick = {},
        navigateToProfileEdit = {}
    )
}