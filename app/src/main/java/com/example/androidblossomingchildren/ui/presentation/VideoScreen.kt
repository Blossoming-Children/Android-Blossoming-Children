@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.androidblossomingchildren.Destinations
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
                onNavigationClick = onNavigateToBack
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Button(
                onClick = { onNavigateToDetail("1") },
            ) {
                Text(text = "영상 1번")
            }
            Button(
                onClick = { onNavigateToDetail("2") },
            ) {
                Text(text = "영상 2번")
            }
        }
    }
}
