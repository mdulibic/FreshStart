package com.example.freshstart.view.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.Blue
import com.example.freshstart.ui.theme.Disabled
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.ui.theme.Green
import com.example.freshstart.view.customView.ActionButton
import com.example.freshstart.view.customView.MultilineTextField
import com.example.freshstart.view.customView.Step
import com.example.freshstart.viewModels.RegistrationViewModel

@Composable
fun AboutRegistrationScreen(registrationViewModel: RegistrationViewModel) {
    AboutRegistrationLayout(
        onStartJourneyClick = registrationViewModel::onStartJourneyClicked
    )
}

@Composable
fun AboutRegistrationLayout(
    onStartJourneyClick: () -> Unit
) {
    var input: String by rememberSaveable { mutableStateOf("") }
    val isEnabled = remember {
        derivedStateOf {
            input.isNotBlank()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                for (i in 0..2) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Step(modifier = Modifier.weight(1f), isActive = i == 2)
                }
            }
            Image(
                modifier = Modifier.size(256.dp).padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.lumiere_girl_thinking_near_question_mark),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(R.string.tells_something_about_yourself),
                style = MaterialTheme.typography.h2,
                color = Blue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            MultilineTextField(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                label = R.string.short_description,
                placeholder = { Text(text = stringResource(id = R.string.about_description_placeholder)) },
                onChange = { input = it },
                value = input
            )
        }
        ActionButton(
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Green,
                disabledBackgroundColor = Disabled
            ),
            isEnabled = isEnabled.value,
            text = R.string.start_journey,
            onClick = onStartJourneyClick
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun AboutRegistrationLayoutPreview() {
    FreshStartTheme {
        AboutRegistrationLayout(
            onStartJourneyClick = {}
        )
    }
}