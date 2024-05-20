@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.androidblossomingchildren.ui.ThemePreviews
import com.example.androidblossomingchildren.ui.theme.TionTheme

@Composable
fun TionTopAppBar(
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    title: @Composable () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = title,
        colors = colors,
        modifier = modifier,
    )
}

@Composable
fun TionTopAppBarBack(
    navigationIcon: ImageVector,
    navigationIconContentDescription: String,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = { },
) {
    CenterAlignedTopAppBar(
        title = title,
        navigationIcon = {
            IconButton(
                onClick = onNavigationClick,
            ) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                )
            }
        },
        actions = actions,
        colors = colors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun TionTopAppBarPreview() {
    TionTheme {
        TionBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            TionTopAppBar(
                title = {
                    Text(text = "Title")
                },
                modifier = Modifier,
            )
        }
    }
}

@ThemePreviews
@Composable
private fun TionTopAppBarBackPreview() {
    TionTheme {
        TionBackground(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
        ) {
            TionTopAppBarBack(
                title = {
                    Text(text = "Title")
                },
                modifier = Modifier,
                navigationIcon = Icons.Rounded.ArrowBack,
                navigationIconContentDescription = "",
                onNavigationClick = { },
            )
        }
    }
}
