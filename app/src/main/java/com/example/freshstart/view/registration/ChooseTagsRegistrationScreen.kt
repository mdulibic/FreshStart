package com.example.freshstart.view.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.*
import com.example.freshstart.view.customView.ActionButton
import com.example.freshstart.view.customView.StaggeredGridItem
import com.example.freshstart.view.customView.Step
import com.example.freshstart.viewModels.RegistrationViewModel

@Composable
fun ChooseTagsRegistrationScreen(registrationViewModel: RegistrationViewModel) {
    ChooseTagsRegistrationLayout(
        onProceedClick = registrationViewModel::onProceedToAboutStep,
        tags = registrationViewModel.tags
    )
}


@Composable
fun ChooseTagsRegistrationLayout(
    onProceedClick: () -> Unit,
    tags: List<String>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                for (i in 0..2) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Step(modifier = Modifier.weight(1f), isActive = i == 1)
                }
            }
            Image(
                modifier = Modifier.size(256.dp).padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.lumiere_woman_writing_down_ideas),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(R.string.why_are_you_here),
                style = MaterialTheme.typography.h2,
                color = Blue,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(R.string.choose_topics),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            StaggeredGridView(items = tags)
        }
        ActionButton(
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Green,
                disabledBackgroundColor = Disabled
            ),
            text = R.string.proceed,
            onClick = onProceedClick
        )
    }
}

@Composable
private fun StaggeredGridView(items: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) { item ->
            val (selected, setSelected) = remember { mutableStateOf(false) }
            StaggeredGridItem(
                text = item,
                selected = selected,
                onClick = { setSelected(!selected) }
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun ChooseTagsRegistrationLayoutPreview() {
    FreshStartTheme {
        ChooseTagsRegistrationLayout(
            tags = listOf(
                "Sport",
                "Health",
                "Lifestyle",
                "Anxiety",
                "Habits",
                "Support",
                "Health",
                "Friendships",
                "Addictions"
            ),
            onProceedClick = {}
        )
    }
}