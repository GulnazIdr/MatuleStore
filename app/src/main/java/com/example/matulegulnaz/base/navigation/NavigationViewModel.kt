package com.example.matulegulnaz.base.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : ViewModel() {

    private val onBoardingViewed = navigationRepository.getOnBoardingViewed()
    private val loggedInState = navigationRepository.getLoggedIn()

    val navigationState: StateFlow<OnBoardingNavigationState> =
        onBoardingViewed.combine(loggedInState) { isViewed, isLoggedIn ->
            when{
                isViewed && isLoggedIn -> OnBoardingNavigationState.NavigateToHome
                isViewed && !isLoggedIn -> OnBoardingNavigationState.NavigateToSignIn
                !isViewed && !isLoggedIn -> OnBoardingNavigationState.NavigateToOnBoardingFirst
                else -> OnBoardingNavigationState.NavigateToHome
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = OnBoardingNavigationState.NavigateToOnBoardingFirst
        )

    fun setOnBoardingViewed(isViewed: Boolean){
        viewModelScope.launch {
            navigationRepository.saveOnBoardingViewed(isViewed)
        }
    }

    fun setLoggedIn(isLoggedIn: Boolean){
        viewModelScope.launch {
            navigationRepository.saveLoggedIn(isLoggedIn)
        }
    }
}