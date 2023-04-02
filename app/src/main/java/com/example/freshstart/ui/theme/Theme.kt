package com.example.freshstart.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black


private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = Pink,
    secondary = Green,
    secondaryVariant = Blue,
    background = White,
    surface = White,
    error = Red,
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black,
    onError = White
)

private val DarkColorPalette = LightColorPalette

@Composable
fun FreshStartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lightColors: Colors = LightColorPalette,
    darkColors: Colors = DarkColorPalette,
    typography: androidx.compose.material.Typography = Typography,
    shapes: Shapes = Shapes,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColors else lightColors

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}