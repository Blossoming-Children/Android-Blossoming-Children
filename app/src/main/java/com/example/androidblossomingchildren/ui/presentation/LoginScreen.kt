package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
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

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToAccountFind: () -> Unit,
    onNavigateToSignUp: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val isButtonEnabled by remember { derivedStateOf { email.isNotEmpty() && password.isNotEmpty() } }

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
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    image
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 로그인 버튼
        TionButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                // if(!CheckEmailForm())
                //  Toast Message: 이메일 형식으로 입력해주세요
                // else if(CheckAccount())
                onNavigateToHome()
                // else
                //  Toast Message: 계정이 올바르지 않습니다
            },
            enabled = isButtonEnabled, // 이메일이 비어 있으면 비활성화
            content = {
                Text(
                    text = "로그인",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.surface,
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
                text = "계정 찾기",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable {
                    onNavigateToAccountFind()
                },
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "회원가입",
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.clickable {
                    onNavigateToSignUp()
                },
            )
        }
    }
}

fun CheckEmailForm() {
    // if(이메일 형식일 경우)
    //      return true  // Toast Message: 이메일 형식으로 작성해주세요
    // else
    //      return false
}
fun CheckAccount() {
//      if(계정이 존재할 경우)
//          onNavigateToHome()  // 계정 정보 넘겨주기
//      else
//          Toast Message: 계정이 존재하지 않습니다
}
