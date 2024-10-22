package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import com.example.androidblossomingchildren.util.theme.TionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordResetScreen() {
    var selectedTab by remember { mutableStateOf(0) } // 0: 아이디 찾기, 1: 비밀번호 찾기
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(
                        text = "계정 찾기",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
                modifier = Modifier
                    .padding(vertical = 15.dp),
                onNavigationClick = {},
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            // 상단 탭 (아이디 찾기, 비밀번호 찾기)
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                ) {
                    Text(
                        text = "아이디 찾기",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                ) {
                    Text(
                        text = "비밀번호 찾기",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 설명 텍스트
            Text(
                text = "새로운 비밀번호를 입력해주세요",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 첫 번째 비밀번호 입력 필드
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "비밀번호를 입력해주세요",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        val image = if (passwordVisible) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        }
                        Icon(imageVector = image, contentDescription = "비밀번호 가시성 토글")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 두 번째 비밀번호 확인 입력 필드
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = {
                    Text(
                        text = "비밀번호를 한 번 더 입력해주세요",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        val image = if (confirmPasswordVisible) {
                            Icons.Filled.Favorite
                        } else {
                            Icons.Filled.FavoriteBorder
                        }
                        Icon(imageVector = image, contentDescription = "비밀번호 확인 가시성 토글")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 확인 버튼
            TionButton(
                onClick = { /* 비밀번호 변경 로직 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword,
            ) {
                Text(
                    text = "확인",
                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                    fontSize = 16.sp,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordResetScreenPreview() {
    TionTheme {
        PasswordResetScreen()
    }
}
