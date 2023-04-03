package com.example.freshstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.freshstart.navigation.FreshStartNavHost
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.navigation.NavigationEffects
import com.example.freshstart.navigation.composable
import com.example.freshstart.navigation.destination.NoArgsDestination
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.view.SplashScreen
import com.example.freshstart.view.WelcomeScreen
import com.example.freshstart.view.loginRegistration.LoginScreen
import com.example.freshstart.viewModels.LoginViewModel
import com.example.freshstart.viewModels.SplashViewModel
import com.example.freshstart.viewModels.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: IAppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavigationEffects(
                navigationChannel = appNavigator.navigationChannel,
                navHostController = navController
            )
            FreshStartTheme {
                Scaffold {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colors.background
                    ) {
                        FreshStartNavHost(
                            navController = navController,
                            startDestination = NoArgsDestination.SplashScreen
                        ) {
                            composable(destination = NoArgsDestination.SplashScreen) {
                                val viewModel: SplashViewModel = hiltViewModel()
                                SplashScreen(splashViewModel = viewModel)
                            }
                            composable(destination = NoArgsDestination.WelcomeScreen) {
                                val viewModel: WelcomeViewModel = hiltViewModel()
                                WelcomeScreen(welcomeViewModel = viewModel)
                            }
                            composable(destination = NoArgsDestination.LoginScreen) {
                                val viewModel: LoginViewModel = hiltViewModel()
                                LoginScreen(loginViewModel = viewModel)
                            }
                        }
                    }
                }

            }
        }
    }
}