package com.example.androidblossomingchildren.util.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.Destinations
import com.example.androidblossomingchildren.navigateSingleTopTo
import com.example.androidblossomingchildren.util.ThemePreviews
import com.example.androidblossomingchildren.util.icon.TionIcons
import com.example.androidblossomingchildren.util.theme.TionTheme

@Composable
fun RowScope.TionNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        colors =
        NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.onSecondaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.secondaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
    )
}

@Composable
fun TionNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
        contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
        content = content,
    )
}

@Composable
fun RowScope.TionNavigationBarContent(
    currentScreen: Destinations,
    navController: NavHostController,
){
    val items = listOf("Home", "Stamp", "MyPage")
    val icons =
        listOf(
            TionIcons.Home,
            TionIcons.Stamp,
            TionIcons.MyPage,
        )
    val selectedIcons =
        listOf(
            TionIcons.HomeSelected,
            TionIcons.StampSelected,
            TionIcons.MyPageSelected,
        )

    items.forEachIndexed { index, _ ->
        TionNavigationBarItem(
            selected = currentScreen.route == items[index],
            onClick = {
                navController.navigateSingleTopTo(items[index])
            },
            icon = {
                Icon(
                    painterResource(id = icons[index]),
                    contentDescription = null,
                )
            },
            selectedIcon = {
                Icon(
                    painterResource(id = selectedIcons[index]),
                    contentDescription = null,
                )
            },
        )
    }
}

@ThemePreviews
@Composable
private fun TionNavigationBarPreview() {
    val items = listOf("Home", "Stamp", "MyPage")
    val icons =
        listOf(
            TionIcons.Home,
            TionIcons.Stamp,
            TionIcons.MyPage,
        )
    val selectedIcons =
        listOf(
            TionIcons.HomeSelected,
            TionIcons.StampSelected,
            TionIcons.MyPageSelected,
        )

    TionTheme {
        TionNavigationBar {
            items.forEachIndexed { index, _ ->
                TionNavigationBarItem(
                    selected = index == 1,
                    onClick = { },
                    icon = {
                        Icon(
                            painterResource(id = icons[index]),
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painterResource(id = selectedIcons[index]),
                            contentDescription = null,
                        )
                    },
                )
            }
        }
    }
}
