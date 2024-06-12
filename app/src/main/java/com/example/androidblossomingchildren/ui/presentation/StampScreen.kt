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
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.util.base.Destinations
import com.example.androidblossomingchildren.util.component.TionNavigationBar
import com.example.androidblossomingchildren.util.component.TionNavigationBarContent
import com.example.androidblossomingchildren.util.component.TionTopAppBar

@Composable
fun StampScreen(
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            TionTopAppBar(
                title = {
                    Text(text = Destinations.Stamp.route)
                },
            )
        },
        bottomBar = {
            TionNavigationBar {
                TionNavigationBarContent(
                    Destinations.Stamp,
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
            Button(onClick = { }) {
                Text(text = "현재 화면 스탬프 화면")
            }
        }
    }
}
