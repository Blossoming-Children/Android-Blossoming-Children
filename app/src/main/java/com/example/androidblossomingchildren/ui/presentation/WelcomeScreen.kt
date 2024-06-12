package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton

@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit,
) {
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 45.dp),
        ) {
            Box(
                modifier = Modifier.size(225.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_main_character),
                    contentDescription = "Main Character",
                )
            }
            TionButton(
                onClick = onNavigateToHome,
                content = {
                    Text(
                        text = "Go to HomePage",
                        fontSize = 20.sp,
                    )
                },
            )
        }
    }
}
