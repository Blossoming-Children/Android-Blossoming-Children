@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.Destinations
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionNavigationBar
import com.example.androidblossomingchildren.util.component.TionNavigationBarContent
import com.example.androidblossomingchildren.util.component.TionTopAppBar

@Composable
fun HomeScreen(
    navController: NavHostController,
    onNavigateToVideo: () -> Unit,
) {
    Scaffold(
        topBar = {
            TionTopAppBar(
                title = {
                    Text(text = Destinations.Home.route)
                },
            )
        },
        bottomBar = {
            TionNavigationBar {
                TionNavigationBarContent(
                    Destinations.Home,
                    navController,
                )
            }
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            TionButton(
                onClick = onNavigateToVideo,
                content = {
                    Text(
                        text = "Go to VideoPage",
                        fontSize = 20.sp,
                    )
                },
            )
        }
    }
}
