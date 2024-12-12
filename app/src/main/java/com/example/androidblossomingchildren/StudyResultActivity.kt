package com.example.androidblossomingchildren

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.theme.Black300
import com.example.androidblossomingchildren.util.theme.Green500
import com.example.androidblossomingchildren.util.theme.TionTheme

class StudyResultActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemText = intent.getStringExtra("ITEM_TEXT") ?: "기본값"

        setContent {
            TionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ResultScreen(this@StudyResultActivity, itemText)
                }
            }
        }
    }
}

@Composable
fun ResultScreen(activity: ComponentActivity, itemText: String) {
    Scaffold(
        topBar = {
            DetailTopAppBar(title = itemText, onBackClicked = { activity.finish() })
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 100.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "결과",
                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                        fontSize = 32.sp,
                        modifier = Modifier
                            .padding(start = 30.dp),
                    )

                    Divider(modifier = Modifier.fillMaxWidth())

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "일치도",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                    modifier = Modifier.padding(bottom = 8.dp),
                                )
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(100.dp),
                                ) {
                                    CircularProgress(
                                        progress = 0.82f,
                                        modifier = Modifier.size(100.dp),
                                    )
                                    Text(
                                        text = "82%",
                                        fontSize = 24.sp,
                                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                    )
                                }
                            }
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.weight(1f),
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = "학습 횟수",
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                )
                                Box(
                                    modifier = Modifier.size(100.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = "1회",
                                        fontSize = 24.sp,
                                        fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                    )
                                }
                            }
                        }
                    }

                    Divider(modifier = Modifier.fillMaxWidth())

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.graph),
                            contentDescription = "그래프",
                            modifier = Modifier
                                .align(Alignment.Center),
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    TionButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(32.dp)
                            .width(300.dp)
                            .height(75.dp),
                    ) {
                        Text(
                            text = "도장 받기",
                            fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                            fontSize = 20.sp,
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun CircularProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Black300,
    progressColor: Color = Green500,
    backgroundStrokeWidth: Dp = 10.dp,
    progressStrokeWidth: Dp = 13.dp,
) {
    val backgroundStroke = with(LocalDensity.current) { backgroundStrokeWidth.toPx() }
    val progressStroke = with(LocalDensity.current) { progressStrokeWidth.toPx() }

    Canvas(modifier = modifier) {
        val radius = (size.minDimension - maxOf(backgroundStroke, progressStroke)) / 2
        val center = Offset(size.width / 2, size.height / 2)

        drawCircle(
            color = backgroundColor,
            radius = radius,
            center = center,
            style = Stroke(width = backgroundStroke),
        )

        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = 360 * progress,
            useCenter = false,
            style = Stroke(width = progressStroke, cap = StrokeCap.Butt),
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2),
        )
    }
}
