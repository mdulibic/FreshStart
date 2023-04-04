package com.example.freshstart.view.customView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.BackgroundInput
import com.example.freshstart.ui.theme.Blue
import com.example.freshstart.ui.theme.White

@Composable
fun MultilineTextField(
    modifier: Modifier = Modifier,
    label: Int,
    value: String,
    onChange: (String) -> Unit = {},
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = BackgroundInput,
        placeholderColor = White,
        textColor = Blue,
        focusedIndicatorColor = BackgroundInput,
        unfocusedIndicatorColor = BackgroundInput
    ),
    placeholder: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1
) {
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(label),
            style = MaterialTheme.typography.body1,
            color = Blue
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth().background(BackgroundInput, MaterialTheme.shapes.small)
                .height(120.dp),
            value = value,
            onValueChange = onChange,
            placeholder = placeholder,
            maxLines = 4,
            colors = colors,
            textStyle = textStyle,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    MultilineTextField(
        value = "John Doe",
        label = R.string.username,
        onChange = {},
        textStyle = MaterialTheme.typography.subtitle1,
        placeholder = {},
    )
}
