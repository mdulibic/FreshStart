package com.example.freshstart.viewModels

import androidx.lifecycle.viewModelScope
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.navigation.destination.NoArgsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
): BaseViewModel() {

    init {
        navigateToWelcomeScreen()
    }

    private fun navigateToWelcomeScreen() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.WelcomeScreen())
        }
    }
}