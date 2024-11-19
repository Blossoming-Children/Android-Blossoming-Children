package com.example.androidblossomingchildren.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.R
import kotlinx.coroutines.delay

@Composable
fun TionToast(
    messageTxt: String,
    onDismiss: () -> Unit,
) {
    // 3초 동안 토스트를 유지한 후 onDismiss 호출로 자동 사라지게 함
    LaunchedEffect(Unit) {
        delay(3000L) // 3초 대기
        onDismiss()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0x99737E91),
                shape = RoundedCornerShape(size = 8.dp),
            )
            .padding(12.dp),
    ) {
        Text(
            text = messageTxt,
            color = Color(0x991D2025),
            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

// @Preview(showBackground = true)
// @Composable
// fun ToastMessageScreenPreview() {
//    TionToast("로그아웃 되었습니다.")
// }
