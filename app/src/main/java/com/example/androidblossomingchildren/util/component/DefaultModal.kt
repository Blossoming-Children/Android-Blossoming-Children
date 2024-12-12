package com.example.androidblossomingchildren.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.androidblossomingchildren.R

@Composable
fun TionDefaultModal() {
    Dialog(onDismissRequest = { }) {
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
                    painter = painterResource(id = R.drawable.img_stamp),
                    contentDescription = "Complete",
                    modifier = Modifier.size(128.dp),
                )
                Spacer(Modifier.height(20.dp))
                TionButton(
                    onClick = { },
                    modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(10.dp),
                    content = {
                        Text(
                            text = "도장 받기",
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun TionModalCheck(
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        TionBackground(
            modifier = Modifier
                .height(150.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(16.dp)),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "로그아웃 하시겠습니까?",
                    modifier = Modifier.padding(8.dp),
                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                    fontSize = 25.sp,
                )
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onTertiary,
                            contentColor = MaterialTheme.colorScheme.surface,
                        ),
                        onClick = { onCancel() },
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(10.dp),
                        content = {
                            Text(
                                text = "취소",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            )
                        },
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    TionButton(
                        onClick = { onConfirm() },
                        modifier = Modifier.width(110.dp),
                        shape = RoundedCornerShape(10.dp),
                        content = {
                            Text(
                                text = "확인",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            )
                        },
                    )
                }
            }
        }
    }
}

// @Preview
// @Composable
// private fun TionModalPreview() {
//    TionTheme {
//        TionBackground(
//            modifier = Modifier
//                .size(200.dp),
//        ) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                // TionDefaultModal()
//                TionModalCheck()
//            }
//        }
//    }
// }
