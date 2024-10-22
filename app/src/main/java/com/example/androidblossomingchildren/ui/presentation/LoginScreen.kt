package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.theme.TionTheme

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 제목 및 로고
        Text(
            text = "아이조아",
            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "로그인",
            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(36.dp))

        // 이메일 입력 필드
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "이메일",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력 필드
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "비밀번호",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = "Toggle Password Visibility")
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 비밀번호 찾기 텍스트
        Text(
            text = "비밀번호 찾기",
            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* 비밀번호 찾기 로직 */ },
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 로그인 버튼
        TionButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = { },
            content = {
                Text(
                    text = "로그인",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                )
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 하단 링크 (이메일 찾기, 회원가입)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "이메일 찾기",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable { /* 이메일 찾기 로직 */ },
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "회원가입",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable { /* 회원가입 로직 */ },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TionTheme {
        LoginScreen()
    }
}
