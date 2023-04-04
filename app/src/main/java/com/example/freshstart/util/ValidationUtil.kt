package com.example.freshstart.util

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
    return emailRegex.matches(this)
}

fun String.hasValidPasswordLength(): Boolean {
    return this.length >= 8
}

fun String.isSameAs(other: String): Boolean {
    return this == other
}
