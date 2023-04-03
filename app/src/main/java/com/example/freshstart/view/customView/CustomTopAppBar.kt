package com.example.freshstart.view.customView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.ui.theme.Blue
import com.example.freshstart.ui.theme.FreshStartTheme

class NavigationItem(val icon: @Composable () -> Unit, val action: () -> Unit)

@Composable
fun CustomTopAppBar(
    navigationItem: NavigationItem? = null
) {
    TopAppBar(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = Color.White,
        contentColor = Blue,
        title = { Text(text = "") },
        navigationIcon = navigationItem?.let {
            { IconButton(onClick = it.action, content = it.icon) }
        }
    )
}

@Preview
@Composable
private fun CustomTopAppBarPreview() {
    FreshStartTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CustomTopAppBar(
                navigationItem = NavigationItem(
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            contentDescription = null
                        )
                    },
                    {}
                )
            )
        }
    }
}
