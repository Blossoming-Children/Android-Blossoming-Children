package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton

@Composable
fun SignUpCompleteScreen(
    onNavigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // 상단 타이틀
            Text(
                text = "아이조아",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "회원가입",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 진행 상태 ProgressBar
            LinearProgressIndicator(
                progress = 1.0f, // 진행 상태 (0.0 ~ 1.0, 66% 진행)
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Column(
            modifier = Modifier.size(320.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // 이미지 표시
            Image(
                painter = painterResource(id = R.drawable.img_main_character), // 이미지 리소스를 이곳에 넣으세요
                contentDescription = "회원가입 성공 이미지",
                modifier = Modifier.size(225.dp),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(30.dp))
            // 완료 텍스트
            Text(
                text = "회원가입이 완료되었습니다!",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                textAlign = TextAlign.Center,
            )
        }

        // "로그인 하기" 버튼
        TionButton(
            onClick = { onNavigateToLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Text(
                text = "로그인 하기",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun SignUpCompleteScreenPreview() {
//    TionTheme {
//        SignUpCompleteScreen()
//    }
// }
