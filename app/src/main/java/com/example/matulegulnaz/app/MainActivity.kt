package com.example.matulegulnaz.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.matulegulnaz.base.navigation.NavigationGraph
import com.example.matulegulnaz.base.navigation.NavigationViewModel
import com.example.matulegulnaz.presentation.authorization.common.viewmodel.AuthViewModel
import com.example.matulegulnaz.ui.theme.MatuleGulnazTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navViewModel: NavigationViewModel by viewModels()
        val authViewModel: AuthViewModel by viewModels()
        setContent {
            MatuleGulnazTheme {
                NavigationGraph(
                    navigationViewModel = navViewModel,
                    authViewModel = authViewModel
                )
            }
        }
    }
}


