@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import kotlinx.coroutines.delay

@Composable
fun AccountFindScreen(
    onNavigateToPasswordReset: () -> Unit,
    onNavigateToBack: () -> Unit,
) {
    var selectedTab by remember { mutableStateOf(0) } // 0: 아이디 찾기, 1: 비밀번호 찾기
    var email by remember { mutableStateOf("") }
    var isAccountFound by remember { mutableStateOf(false) }
    var accountInfo by remember { mutableStateOf("") }

    var isCodeSent by remember { mutableStateOf(false) } // 인증번호가 전송되었는지 여부
    var verificationCode by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(60) } // 타이머 상태 (60초)
    var canResendCode by remember { mutableStateOf(false) } // 다시 받기 버튼 활성화 여부

    val coroutineScope = rememberCoroutineScope()

    // 타이머 동작
    LaunchedEffect(timer) {
        if (isCodeSent && timer > 0) {
            delay(1000L)
            timer -= 1
            if (timer == 0) {
                canResendCode = true // 시간이 다 되면 "다시 받기" 활성화
            }
        }
    }

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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 25.dp, end = 25.dp),
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

            if (selectedTab == 0) {
                if (!isAccountFound) {
                    // 이메일 입력 필드와 버튼 (계정 찾기 상태)
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = if (selectedTab == 0) "계정 찾기" else "비밀번호 찾기",
                            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // 이메일 입력 필드
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = {
                                Text(
                                    text = "이메일을 입력해주세요",
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // "계정 찾기" 버튼
                        TionButton(
                            onClick = {
                                // 계정 찾기 로직 (여기서는 가상으로 처리)
                                // if(CheckAccount()의 return 값이 true)
                                isAccountFound = true
                                accountInfo = "$email 계정이 존재합니다"
                                // else
                                //  isAccountFound = true
                                //  accountInfo = "$email 계정이 존재하지 않습니다"
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            enabled = email.isNotEmpty(),
                        ) {
                            Text(
                                text = if (selectedTab == 0) "계정 찾기" else "비밀번호 찾기",
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
            } else if (selectedTab == 1) {
                // 비밀번호 찾기 화면
                Column {
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "이메일 인증 후\n비밀번호를 재설정 할 수 있어요",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 이메일 입력 필드
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = {
                            Text(
                                text = "이메일을 입력해주세요",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        enabled = !isCodeSent, // 인증번호가 전송되면 이메일 필드 비활성화
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (!isCodeSent) {
                        // "인증번호 받기" 버튼
                        TionButton(
                            onClick = {
                                isCodeSent = true
                                timer = 60 // 타이머 초기화
                                canResendCode = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            enabled = email.isNotEmpty(),
                        ) {
                            Text(
                                text = "인증번호 받기",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                fontSize = 16.sp,
                                color = Color.White,
                            )
                        }
                    } else {
                        // 인증번호 입력 필드와 확인 버튼 표시
                        OutlinedTextField(
                            value = verificationCode,
                            onValueChange = { verificationCode = it },
                            placeholder = {
                                Text(
                                    text = "인증번호 6자리를 입력하세요",
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // 타이머 표시
                            Text(
                                text = String.format("00:%02d", timer),
                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )

                            // "다시 받기" 버튼
                            /* 로직 구현 아직 안 됨 */
                            TionButton(
                                onClick = {
                                    if (canResendCode) {
                                        timer = 60 // 타이머 초기화
                                        canResendCode = false
                                    }
                                },
                                modifier = Modifier.height(50.dp),
                                enabled = canResendCode, // 타이머가 종료되었을 때만 활성화
                            ) {
                                Text(
                                    text = "다시 받기",
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                    fontSize = 16.sp,
                                    color = Color.White,
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // "확인" 버튼
                        TionButton(
                            onClick = {
                                onNavigateToPasswordReset()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            enabled = verificationCode.length == 6,
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
        }
    }
}
