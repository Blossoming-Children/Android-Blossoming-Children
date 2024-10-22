@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.base.Destinations
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
                    Text(
                        text = Destinations.Home.route,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier
                    .padding(vertical = 15.dp),
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
            Image(
                painter = painterResource(id = R.drawable.img_character_exercise),
                contentDescription = "Exercise Character",
                modifier = Modifier
                    .size(225.dp),
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(10.dp),
            )
            TionButton(
                onClick = onNavigateToVideo,
                content = {
                    Text(
                        text = "동작 교육",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
            TionButton(
                enabled = false,
                onClick = onNavigateToVideo,
                content = {
                    Text(
                        text = "안전 교육",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(10.dp),
            )
        }
    }
}
