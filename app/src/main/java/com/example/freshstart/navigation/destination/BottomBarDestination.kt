package com.example.freshstart.navigation.destination


sealed class BottomBarDestination(
    route: String,
    val icon: Int
) : NoArgsDestination(route) {
    companion object {
        val subClasses: List<BottomBarDestination> = listOf(

        )
    }
}
