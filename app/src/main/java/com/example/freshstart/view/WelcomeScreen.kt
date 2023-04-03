package com.example.freshstart.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.ui.theme.Green
import com.example.freshstart.view.customView.ActionButton
import com.example.freshstart.viewModels.WelcomeViewModel

@Composable
fun WelcomeScreen(welcomeViewModel: WelcomeViewModel) {
    WelcomeLayout(
        onClickLogin = welcomeViewModel::onLoginClicked,
        onClickJoin = welcomeViewModel::onJoinClicked
    )
}

@Composable
fun WelcomeLayout(
    onClickLogin: () -> Unit,
    onClickJoin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Green),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(320.dp).padding(top = 16.dp),
            painter = painterResource(id = R.drawable.lumiere_man_and_woman_downloading_information),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.welcome_title),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Left
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.welcome_subtitle),
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.weight(1f))
        JoinSection(
            onClickJoin = onClickJoin,
            onClickLogin = onClickLogin
        )
    }
}

@Composable
private fun JoinSection(
    onClickLogin: () -> Unit,
    onClickJoin: () -> Unit
) {
    ActionButton(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = R.string.join_now,
        onClick = onClickJoin
    )
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(R.string.already_part_of_the_community),
        color = MaterialTheme.colors.onPrimary,
        style = MaterialTheme.typography.subtitle1,
        textAlign = TextAlign.Center
    )
    Text(
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp).clickable { onClickLogin() },
        text = stringResource(R.string.login),
        color = MaterialTheme.colors.onPrimary,
        style = MaterialTheme.typography.subtitle2,
        textAlign = TextAlign.Center
    )
}

@Preview(backgroundColor = 0xFF000000, showBackground = true)
@Composable
private fun WelcomeLayoutPreview() {
    FreshStartTheme {
        WelcomeLayout(
            onClickJoin = {},
            onClickLogin = {}
        )
    }
}