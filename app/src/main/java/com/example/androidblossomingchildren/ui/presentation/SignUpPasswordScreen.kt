package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.theme.TionTheme

@Composable
fun SignUpPasswordScreen() {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val isPasswordValid by remember {
        derivedStateOf {
            password.length >= 10 && password.any { it.isDigit() } && password.any { it.isLetter() }
        }
    }

    val isConfirmPasswordValid by remember {
        derivedStateOf { confirmPassword == password }
    }

    val isButtonEnabled by remember {
        derivedStateOf { isPasswordValid && isConfirmPasswordValid }
    }

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
                progress = 0.75f, // 진행 상태 (0.0 ~ 1.0)
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 설명 텍스트
            Text(
                text = "비밀번호는 10자 이상으로\n영문과 숫자를 혼합하여 입력해주세요.",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 비밀번호 입력 필드
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "비밀번호를 입력하세요",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val icon = if (passwordVisible) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 비밀번호 확인 입력 필드
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = {
                    Text(
                        text = "비밀번호를 한 번 더 입력하세요",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.tertiary,
                    )
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        val icon = if (confirmPasswordVisible) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 비밀번호 유효성 조건 표시 (영문, 숫자, 10자 이상)
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "✓ 영문",
                    color = if (password.any { it.isLetter() }) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "✓ 숫자",
                    color = if (password.any { it.isDigit() }) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "✓ 10자 이상",
                    color = if (password.length >= 10) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                    fontSize = 16.sp,
                )
            }
        }

        // "다음" 버튼
        TionButton(
            onClick = { /* 다음 단계로 진행하는 로직 */ },
            enabled = isButtonEnabled, // 비밀번호 유효성 확인
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

@Preview(showBackground = true)
@Composable
fun SignUpPasswordScreenPreview() {
    TionTheme {
        SignUpPasswordScreen()
    }
}
