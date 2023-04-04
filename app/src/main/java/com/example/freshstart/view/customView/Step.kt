package com.example.freshstart.view.customView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.freshstart.ui.theme.BackgroundInput
import com.example.freshstart.ui.theme.Pink

@Composable
fun Step(modifier: Modifier, isActive: Boolean) {
    Box(
        modifier = modifier then Modifier
            .fillMaxWidth()
            .height(14.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = if (isActive) Pink else BackgroundInput)
    )
}