package com.example.freshstart.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.freshstart.R

val montserrat = FontFamily(
    Font(R.font.montserrat_regular, weight = FontWeight.Normal),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_light, weight = FontWeight.Light),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_semi_bold, weight = FontWeight.SemiBold)
)

val Typography = androidx.compose.material.Typography(
    h1 = TextStyle(
        fontSize = 48.sp,
        lineHeight = 56.sp
    ).defaultFont().bold(),
    h2 = TextStyle(
        fontSize = 32.sp,
        lineHeight = 36.sp
    ).defaultFont().bold(),
    h3 = TextStyle(
        fontSize = 28.sp,
        lineHeight = 33.sp
    ).defaultFont().bold(),
    h4 = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp
    ).defaultFont().bold(),
    subtitle1 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ).defaultFont().bold(),
    subtitle2 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ).defaultFont().medium(),
    body1 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp
    ).defaultFont().regular(),
    body2 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ).defaultFont().regular(),
    button = TextStyle(
        fontSize = 20.sp,
        lineHeight = 20.sp
    ).defaultFont().medium(),
    caption = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp
    ).defaultFont().regular(),
    overline = TextStyle(
        fontSize = 10.sp,
        lineHeight = 16.sp
    ).defaultFont().medium()
)

fun TextStyle.bold(): TextStyle {
    return this.copy(
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    )
}

fun TextStyle.semiBold(): TextStyle {
    return this.copy(
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal
    )
}

fun TextStyle.medium(): TextStyle {
    return this.copy(
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal
    )
}

fun TextStyle.regular(): TextStyle {
    return this.copy(
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal
    )
}

fun TextStyle.defaultFont(): TextStyle {
    return this.copy(
        fontFamily = montserrat
    )
}
