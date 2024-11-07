@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack

@Composable
fun PasswordResetScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
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
                onNavigationClick = onNavigateToBack,
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
            Spacer(modifier = Modifier.height(20.dp))
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
                        if (passwordVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_visibility),
                                modifier = Modifier.size(25.dp),
                                contentDescription = "visible",
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_visibility_off),
                                modifier = Modifier.size(25.dp),
                                contentDescription = "invisible",
                            )
                        }
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
                        if (confirmPasswordVisible) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_visibility),
                                modifier = Modifier.size(25.dp),
                                contentDescription = "visible",
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_visibility_off),
                                modifier = Modifier.size(25.dp),
                                contentDescription = "invisible",
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 확인 버튼
            TionButton(
                onClick = {
                    // if(CheckPasswordForm()의 return 값이 true)
                    onNavigateToLogin() // Toast Message: 비밀번호 재설정 완료
                    // else
                    //  Toast Message: 비밀번호는 10자리 이상의 숫자와 문자의 조합으로 입력해주세요.
                },
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
