package com.example.androidblossomingchildren.ui.presentation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.config.checkEmailForm
import com.example.androidblossomingchildren.ui.config.generateAccessToken
import com.example.androidblossomingchildren.ui.config.loginUser
import com.example.androidblossomingchildren.util.component.TionButton

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToAccountFind: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    context: Context = LocalContext.current,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val isButtonEnabled by remember { derivedStateOf { email.isNotEmpty() && password.isNotEmpty() } }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }

    // SharedPreferences에서 자동 로그인 정보 불러오기
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    LaunchedEffect(Unit) {
        email = sharedPreferences.getString("saved_email", "") ?: ""
        password = sharedPreferences.getString("saved_password", "") ?: ""
        Log.d("Login: ", "자동 로그인 시도 $email, $password")
        if (email.isNotEmpty() && password.isNotEmpty()) {
            val refreshToken = generateAccessToken()
            // 자동 로그인 시도
            loginUser(email, password, refreshToken) { message ->
                toastMessage = message
                showToast = true

                if (message == "로그인에 성공했습니다.") {
                    onNavigateToHome()
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
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
                    val refreshToken = "YOUR_REFRESH_TOKEN" /* TODO: 실제 토큰으로 대체해야 함 */
                    if (!checkEmailForm(email)) {
                        toastMessage = "이메일 형식으로 입력해주세요."
                        showToast = true
                    } else {
                        loginUser(email, password, refreshToken) { message ->
                            toastMessage = message
                            showToast = true

                            // 로그인 성공 시 이메일과 비밀번호 저장
                            if (message == "로그인에 성공했습니다.") {
                                val editor = sharedPreferences.edit()
                                editor.putString("saved_email", email)
                                editor.putString("saved_password", password)
                                editor.apply()

                                onNavigateToHome()
                            }
                        }
                    }
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
        if (showToast) {
            TionToast(
                messageTxt = toastMessage,
                onDismiss = { showToast = false },
            )
        }
    }
}
