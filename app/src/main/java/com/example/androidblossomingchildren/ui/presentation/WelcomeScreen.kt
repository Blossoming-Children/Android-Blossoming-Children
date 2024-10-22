package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.theme.Green400
import com.example.androidblossomingchildren.util.theme.Yellow200

@Composable
fun WelcomeScreen(
    onNavigateToHome: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Yellow200),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .background(Yellow200),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_main_character),
                contentDescription = "Main Character",
                modifier = Modifier.size(225.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
            )
            Card(
                colors = CardDefaults.cardColors(containerColor = Green400),
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 180.dp, topEnd = 180.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "아이조아",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 65.dp),
                    )
                    Text(
                        text = "아이들을 위한 동작 교육과 안전 교육",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier
                            .padding(top = 10.dp),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                    )
                    Image(
                        painter = painterResource(id = R.drawable.img_kakao_login),
                        contentDescription = "Kakao Login",
                        modifier = Modifier
                            .height(48.dp)
                            .width(320.dp)
                            .clickable {
                                onNavigateToHome()
                            },
                    )
                }
            }
        }
    }
}
