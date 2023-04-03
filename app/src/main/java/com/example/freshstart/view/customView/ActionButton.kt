package com.example.freshstart.view.customView

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.ui.theme.FreshStartTheme
import com.example.freshstart.ui.theme.Pink
import com.example.freshstart.R

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    isEnabled: Boolean = true,
    textColor: Color = MaterialTheme.colors.onPrimary,
    border: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Pink,
        disabledBackgroundColor = Pink
    ),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier then Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large),
        colors = colors,
        border = border,
        enabled = isEnabled,
        onClick = { onClick() }
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            color = if (isEnabled) textColor else textColor,
            text = stringResource(id = text),
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    FreshStartTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ActionButton(text = R.string.join_now) { }
        }
    }
}