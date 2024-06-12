package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.util.component.TionButton

@Composable
fun OnBoardingScreen(
    onNavigateToApp: () -> Unit,
) {
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 45.dp),
        ) {
            Text(text = "온보딩 화면")
            TionButton(
                onClick = onNavigateToApp,
                content = {
                    Text(
                        text = "Skip",
                        fontSize = 20.sp,
                    )
                },
            )
        }
    }
}
