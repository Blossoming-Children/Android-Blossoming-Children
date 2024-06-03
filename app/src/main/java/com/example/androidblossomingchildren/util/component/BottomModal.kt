package com.example.androidblossomingchildren.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.theme.Blue_dark
import com.example.androidblossomingchildren.ui.theme.Gray02
import com.example.androidblossomingchildren.ui.theme.Gray03
import com.example.androidblossomingchildren.ui.theme.Green02

@Composable
fun TionButtonModalStamp() {
    var showBottomSheet by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }
    var iconResId by remember { mutableStateOf(R.drawable.settings_name) }
    var iconColor by remember { mutableStateOf(Gray03) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
        IconButton(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .padding(16.dp),
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "Change",
                tint = iconColor,
            )
        }

        if (showBottomSheet) {
            BottomSheetStampDialog(
                onDismissRequest = { showBottomSheet = false },
                inputText = inputText,
                onInputTextChange = { newText -> inputText = newText },
                onIconChange = { newIcon -> iconResId = newIcon },
                onIconColorChange = { newColor -> iconColor = newColor }
            )
        }
    }
}

@Composable
fun BottomSheetStampDialog(
    onDismissRequest: () -> Unit,
    inputText: String,
    onInputTextChange: (String) -> Unit,
    onIconChange: (Int) -> Unit,
    onIconColorChange: (androidx.compose.ui.graphics.Color) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = MaterialTheme.shapes.large,
            tonalElevation = 8.dp,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "목표",
                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Blue_dark,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                )
                OutlinedTextField(
                    value = inputText,
                    onValueChange = onInputTextChange,
                    placeholder = {
                        Text(
                            text = "여기에 입력해 주세요.",
                            color = Gray03,
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Gray02,
                        unfocusedBorderColor = Gray02,
                    ),
                )
                Button(
                    onClick = {
                        onDismissRequest()
                        onIconChange(R.drawable.check_circle)
                        onIconColorChange(Green02)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "변경하기",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                    )
                }
            }
        }
    }
}


@Composable
fun TionBottomModalName() {
    var showBottomSheet by remember { mutableStateOf(false) }
    var nickname by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        IconButton(onClick = { showBottomSheet = true }) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Change Name",
            )
        }

        if (showBottomSheet) {
            BottomSheetNameDialog(
                onDismissRequest = { showBottomSheet = false },
                nickname = nickname,
                onNicknameChange = { newNickname -> nickname = newNickname },
            )
        }
    }
}

@Composable
fun BottomSheetNameDialog(
    onDismissRequest: () -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "닉네임",
                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Blue_dark,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                )
                OutlinedTextField(
                    value = nickname,
                    onValueChange = onNicknameChange,
                    placeholder = {
                        Text(
                            text = "여기에 입력해 주세요.",
                            color = Gray03,
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Gray02,
                        unfocusedBorderColor = Gray02,
                    ),
                )
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "변경하기",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                    )
                }
            }
        }
    }
}
