package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OnBoardingScreen(
    onNavigateToApp: () -> Unit,
) {
    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            val isOnBoarding = true
            LaunchedEffect(isOnBoarding) {
                delay(3000L)
                onNavigateToApp()
            }
            Text(
                text = "온보딩 화면",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
