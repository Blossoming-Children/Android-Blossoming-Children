package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import kotlinx.coroutines.delay

@Composable
fun OnBoardingScreen(
    onNavigateToApp: () -> Unit,
) {
    val isOnBoarding = remember { mutableStateOf(true) }
    val scale = remember { mutableFloatStateOf(0.4f) }
    val textScale = remember { mutableFloatStateOf(0.9f) }
    val translationY = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(isOnBoarding.value) {
        if (isOnBoarding.value) {
            // 애니메이션 설정
            scale.value = 0.8f
            textScale.value = 1.3f
            translationY.value = -200f
            delay(6000L)
            isOnBoarding.value = false
            onNavigateToApp()
        }
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_main_logo),
                contentDescription = "Splash Image",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .scale(
                        animateFloatAsState(
                            targetValue = scale.value,
                            animationSpec = tween(durationMillis = 6000),
                            label = "",
                        ).value,
                    )
                    .graphicsLayer(
                        translationY = animateFloatAsState(
                            targetValue = translationY.value,
                            animationSpec = tween(durationMillis = 6000),
                            label = "",
                        ).value,
                    ),
            )
            Text(
                text = "아이조아",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .scale(
                        animateFloatAsState(
                            targetValue = textScale.value,
                            animationSpec = tween(durationMillis = 6000),
                            label = "",
                        ).value,
                    )
                    .graphicsLayer(
                        translationY = animateFloatAsState(
                            targetValue = translationY.value,
                            animationSpec = tween(durationMillis = 6000),
                            label = "",
                        ).value,
                    ),
            )
        }
    }
}
