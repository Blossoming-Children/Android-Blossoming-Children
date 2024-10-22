@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import com.example.androidblossomingchildren.util.theme.TionTheme

@Composable
fun AccountDeletionScreen() {
    var password by remember { mutableStateOf("") }
    var isAccountRight by remember { mutableStateOf(false) }
    var accountInfo by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(
                        text = "회원 탈퇴",
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 25.dp, end = 25.dp),
        ) {
            if (!isAccountRight) {
                // 이메일 입력 필드와 버튼 (계정 찾기 상태)
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "회원 탈퇴를 위해서\n비밀번호를 다시 한 번 입력해주세요.",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 이메일 입력 필드
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                text = "비밀번호를 입력해주세요",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // "계정 찾기" 버튼
                    Button(
                        onClick = {
                            if (password.isNotEmpty()) {
                                // 계정 찾기 로직 (여기서는 가상으로 처리)
                                isAccountRight = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF38181)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = true, // email.isNotEmpty(),
                    ) {
                        Text(
                            text = "회원 탈퇴",
                            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    }
                }
            } else {
                // 계정 찾은 후 상태
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp),
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = accountInfo,
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountDeletionScreenPreview() {
    TionTheme {
        AccountDeletionScreen()
    }
}
