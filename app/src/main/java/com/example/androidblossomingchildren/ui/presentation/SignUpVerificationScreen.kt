package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton

@Composable
fun SignUpVerificationScreen(
    onNavigateToSignUpNickname: () -> Unit,
) {
    var code by remember { mutableStateOf("") }
    val isButtonEnabled by remember { derivedStateOf { code.length == 6 } }

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
                progress = 0.40f, // 진행 상태 (0.0 ~ 1.0, 66% 진행)
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 설명 텍스트
            Text(
                text = "이메일로 인증 코드가 발송되었습니다.",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 인증 코드 입력 필드
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = {
                    Text(
                        text = "인증 코드 6자리를 입력하세요",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 재발송 텍스트
            Text(
                text = "재발송",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* 재발송 로직 */ },
            )
        }

        // "다음" 버튼
        TionButton(
            onClick = { onNavigateToSignUpNickname() },
            enabled = isButtonEnabled, // 코드가 6자리가 아니면 비활성화
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        ) {
            Text(
                text = "다음",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}
