package com.example.androidblossomingchildren.ui.presentation

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
fun SignUpScreen(
    onNavigateToSignUpVerification: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    val isButtonEnabled by remember { derivedStateOf { email.isNotEmpty() } }

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
                progress = 0.25f, // 진행 상태 (0.0 ~ 1.0)
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 설명 텍스트
            Text(
                text = "회원가입을 위해\n인증 가능한 이메일을 입력해 주세요.",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 이메일 입력 필드
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = "이메일을 입력하세요",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }

        // "다음" 버튼
        TionButton(
            onClick = {
                // if(!CheckEmailForm())
                //  Toast Message: 이메일 형식으로 입력해주세요
                // else if(CheckAccount())
                //  Toast Message: 이미 존재하는 이메일입니다
                // else
                onNavigateToSignUpVerification()
            },
            enabled = isButtonEnabled, // 이메일이 비어 있으면 비활성화
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
