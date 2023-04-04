package com.example.freshstart.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.Blue
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.viewModels.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    HomeLayout()
}

@Composable
fun HomeLayout(

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(256.dp).padding(horizontal = 32.dp, vertical = 16.dp),
            painter = painterResource(id = R.drawable.lumiere_man_and_woman_downloading_information),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            text = stringResource(R.string.something_awesome),
            style = MaterialTheme.typography.h2,
            color = Blue,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            text = stringResource(R.string.new_features_soon),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun HomeLayoutPreview() {
    FreshStartTheme {
        HomeLayout(

        )
    }
}