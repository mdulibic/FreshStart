package com.example.freshstart.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.ui.theme.Green
import com.example.freshstart.viewModels.SplashViewModel

@Composable
fun SplashScreen(splashViewModel: SplashViewModel) {
    SplashLayout()
}

@Composable
fun SplashLayout(

) {
    Column(modifier = Modifier.fillMaxSize().background(color = Green)) {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(horizontal = 40.dp),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun SplashLayoutPreview() {
    FreshStartTheme {
        SplashLayout(

        )
    }
}