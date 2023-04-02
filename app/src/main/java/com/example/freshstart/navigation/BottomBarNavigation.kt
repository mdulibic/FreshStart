package com.example.freshstart.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.freshstart.ui.theme.Green
import com.example.freshstart.ui.theme.Green30

data class BottomNavigationItem(val icon: Int, val isSelected: Boolean, val onClick: () -> Unit)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    isVisible: Boolean
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            Column {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    items.forEach { item ->
                        BottomBarNavigationLayout(
                            icon = item.icon,
                            isSelected = item.isSelected,
                            onClick = item.onClick
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun RowScope.BottomBarNavigationLayout(
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        icon = {
            Icon(
                painterResource(id = icon),
                contentDescription = null
            )
        },
        selectedContentColor = Green,
        unselectedContentColor = Green30,
        selected = isSelected,
        onClick = onClick
    )
}

@Preview
@Composable
private fun BottomNavBarPreview() {

}
