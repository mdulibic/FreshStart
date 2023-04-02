package com.example.freshstart.navigation.destination

sealed class Destination(val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) {
        route
    } else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{$it}") }
        builder.toString()
    }
}
