package com.example.matulegulnaz.presentation.home.profile

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matulegulnaz.R
import com.example.matulegulnaz.domain.user.User
import com.example.matulegulnaz.presentation.components.CommonScaffold
import com.example.matulegulnaz.presentation.components.CustomField
import com.example.matulegulnaz.presentation.home.CustomHomeTopBar
import com.example.matulegulnaz.presentation.home.drawer.DrawerViewmodel
import com.example.matulegulnaz.presentation.home.main.component.CircleLoading
import com.example.matulegulnaz.ui.theme.bluePrimaryColor
import com.example.matulegulnaz.ui.theme.mainButtonColor
import com.example.matulegulnaz.ui.theme.profileGrey
import com.example.matulegulnaz.ui.theme.ralewayFamily

@Composable
fun ProfileEditScreen(
    onBack: () -> Unit,
    onEditPhoto: () -> Unit,
    onBardCode: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    drawerViewmodel: DrawerViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val setProfileResultState = profileViewModel.updateProfileResultState.collectAsState().value
    val fetchUserState = drawerViewmodel.fetchUserDataState.collectAsState().value

    BackHandler { onBack() }

    CommonScaffold(
        snackbarHostState
    ) { padding ->

        setProfileResultState.Display(
            onSuccess = {
                drawerViewmodel.getCurrentUserData()
            },
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
                fetchUserState.Display(
                    onSuccess = { userData ->
                        var name by rememberSaveable { mutableStateOf(userData.name) }
                        var surname by rememberSaveable { mutableStateOf(userData.surname) }
                        var address by rememberSaveable { mutableStateOf(userData.address) }
                        var phoneNumber by rememberSaveable { mutableStateOf(userData.phone) }

                        CustomHomeTopBar(
                            topBarTitle = "Profile",
                            onLeftActionButton = { onBack() },
                            onUserActionClick = { profileViewModel.setCurrentUserMetaData(User(
                                name = name,
                                surname = surname,
                                address = address,
                                phone = phoneNumber
                            )) },
                            actionButtonText = "Save"
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        Image(
                            painter = painterResource(R.drawable.user),
                            contentDescription = "user image",
                            modifier = Modifier.size(96.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "$name $surname",
                            fontSize = 20.sp,
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.W600,
                            color = mainButtonColor
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Edit profile photo",
                            fontSize = 12.sp,
                            fontFamily = ralewayFamily,
                            fontWeight = FontWeight.W600,
                            color = bluePrimaryColor,
                            modifier = Modifier.clickable(onClick = {onEditPhoto()})
                        )

                        Spacer(modifier = Modifier.height(11.dp))

                        Image(
                            painter = painterResource(R.drawable.bar),
                            contentDescription = "user bar",
                            modifier = Modifier
                                .width(337.dp)
                                .height(70.dp)
                                .clickable(onClick = { onBardCode() })
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        CustomField(
                            textValue = name,
                            onValueChanged = { name = it },
                            placeholder = "",
                            isError = false,
                            errorText = "",
                            label = "Name",
                            backgroundColor = profileGrey,
                            showCheck = name.isNotEmpty()
                        )

                        CustomField(
                            textValue = surname,
                            onValueChanged = { surname = it },
                            placeholder = "",
                            isError = false,
                            errorText = "",
                            label = "Surname",
                            backgroundColor = profileGrey,
                            showCheck = surname.isNotEmpty()
                        )

                        CustomField(
                            textValue = address,
                            onValueChanged = { address = it },
                            placeholder = "",
                            isError = false,
                            errorText = "",
                            label = "Address",
                            backgroundColor = profileGrey,
                            showCheck = address.isNotEmpty()
                        )
                        CustomField(
                            textValue = phoneNumber,
                            onValueChanged = { phoneNumber = it },
                            placeholder = "",
                            isError = false,
                            errorText = "",
                            label = "Phone number",
                            backgroundColor = profileGrey,
                            showCheck = phoneNumber.isNotEmpty()
                        )
                    },
                    onError = {},
                    onLoading = {
                        CircleLoading()
                    },
                    onChangeButtonState = {},
                    snackbarHostState = snackbarHostState
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileEditScreenPreview() {
    ProfileEditScreen(
        onBack = {},
        onEditPhoto = {},
        onBardCode = {}
    )
}