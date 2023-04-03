package com.example.freshstart.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.navigation.destination.NoArgsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
): ViewModel() {

    fun onJoinClicked() {
        // TODO: Navigate to registration flow
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.LoginScreen())
        }
    }
}