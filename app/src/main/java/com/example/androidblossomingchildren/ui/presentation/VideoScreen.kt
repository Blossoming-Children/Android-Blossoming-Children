@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidblossomingchildren.util.base.Destinations
import com.example.androidblossomingchildren.util.component.TionGridItem
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack

@Composable
fun VideoScreen(
    onNavigateToDetail: (Any?) -> Unit,
    onNavigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(text = Destinations.Video.route)
                },
                onNavigationClick = onNavigateToBack,
            )
        },
        content = { padding ->
            val itemList = List(20) { "제목 ${it + 1}" }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(all = 16.dp),
                modifier = Modifier.padding(padding),
            ) {
                items(itemList) { item ->
                    TionGridItem(
                        item,
                        onClick = {
                            onNavigateToDetail(item)
                        },
                    )
                }
            }
        },
    )
}
