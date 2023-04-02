package com.example.freshstart.navigation.destination

sealed class NoArgsDestination(route: String) : Destination(route) {
    operator fun invoke(): String = route

    object SplashScreen : NoArgsDestination("SplashScreen")

    object WelcomeScreen : NoArgsDestination("WelcomeScreen")

    object LoginScreen : NoArgsDestination("LoginScreen")
}
