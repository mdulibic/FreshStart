package com.example.freshstart.view.customView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.freshstart.ui.theme.Green
import com.example.freshstart.ui.theme.Pink
import com.example.freshstart.ui.theme.White

@Composable
fun StaggeredGridItem(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) Green else Pink
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color = backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = text,
            style = MaterialTheme.typography.body1,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}