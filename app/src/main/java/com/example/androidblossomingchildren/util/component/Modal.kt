package com.example.androidblossomingchildren.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.util.component.TionBackground
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.theme.TionTheme
import kotlinx.coroutines.delay

@Composable
fun TionModal(
    accuracy: Float,
    isLastPage: Boolean,
    onDismiss: () -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }
    val showOnce = remember { mutableStateOf(false) }

    if (accuracy < 0.8f) {
        LaunchedEffect(Unit) {
            delay(5000L)
            showOnce.value = true
            onDismiss()
        }
    }

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            TionBackground(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    if (accuracy >= 0.8f || showOnce.value) {
                        Image(
                            painter = painterResource(id = R.drawable.character_good),
                            modifier = Modifier.size(128.dp),
                            contentDescription = "Good",
                        )
                        Text(
                            text = "참 잘했어요!",
                            modifier = Modifier.padding(8.dp),
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            fontSize = 25.sp,
                        )
                        TionButton(
                            onClick = { /* event 처리 */ },
                            modifier = Modifier.width(200.dp),
                            shape = RoundedCornerShape(10.dp),
                            content = {
                                Text(
                                    text = "다음",
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                )
                            },
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.character_retry),
                            contentDescription = "Retry",
                            modifier = Modifier.size(128.dp),
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                    append("다시")
                                }
                                append(" 해볼까요")
                            },
                            modifier = Modifier.padding(8.dp),
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            fontSize = 25.sp,
                        )
                    }
                }
            }
        }
    }

    if (isLastPage) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            TionBackground(
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.character),
                        contentDescription = "Complete",
                        modifier = Modifier.size(128.dp),
                    )
                    Text(
                        text = buildAnnotatedString {
                            append("모든 동작 ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                append("완료")
                            }
                            append("!")
                        },
                        modifier = Modifier.padding(8.dp),
                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                        fontSize = 25.sp,
                    )
                    TionButton(
                        onClick = { /* event 처리 */ },
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(10.dp),
                        content = {
                            Text(
                                text = "완료",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            )
                        },
                    )
                }
            }
        }
    }
}

@Preview(apiLevel = 27)
@Composable
private fun TionModalPreview() {
    TionTheme {
        TionBackground(
            modifier = Modifier.size(200.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                TionModal(
                    accuracy = 0.75f,
                    onDismiss = { },
                    isLastPage = false,
                )
            }
        }
    }
}
