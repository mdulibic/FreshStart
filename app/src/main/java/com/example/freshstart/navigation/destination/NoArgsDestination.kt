package com.example.freshstart.navigation.destination

sealed class NoArgsDestination(route: String) : Destination(route) {
    operator fun invoke(): String = route

    object SplashScreen : NoArgsDestination("SplashScreen")

    object WelcomeScreen : NoArgsDestination("WelcomeScreen")

    object LoginScreen : NoArgsDestination("LoginScreen")

    object BasicInfoRegistrationScreen : NoArgsDestination("BasicInfoRegistrationScreen")

    object ChooseTagsRegistrationScreen : NoArgsDestination("ChooseTagsRegistrationScreen")

    object AboutRegistrationScreen : NoArgsDestination("AboutRegistrationScreen")

    object HomeScreen : NoArgsDestination("HomeScreen")
}
