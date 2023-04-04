package com.example.freshstart.viewModels

import androidx.lifecycle.viewModelScope
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.navigation.destination.NoArgsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
) : BaseViewModel() {

    fun onJoinClicked() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.BasicInfoRegistrationScreen())
        }
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.LoginScreen())
        }
    }
}