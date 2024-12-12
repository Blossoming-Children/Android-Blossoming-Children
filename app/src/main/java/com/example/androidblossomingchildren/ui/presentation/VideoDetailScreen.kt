@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.config.extractYouTubeVideoId
import com.example.androidblossomingchildren.ui.config.getEducationDetail
import com.example.androidblossomingchildren.ui.viewmodel.VideoDetailViewModel
import com.example.androidblossomingchildren.util.component.TionAccuracyModal
import com.example.androidblossomingchildren.util.component.TionBackground
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionModalText
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import com.example.androidblossomingchildren.util.component.TionYouTubePlayer

@Composable
fun VideoDetailScreen(
    eduId: Int,
    onNavigateToResult: (Any?) -> Unit,
    onNavigateToBack: () -> Unit,
    viewModel: VideoDetailViewModel,
) {
    val currentPage = remember { mutableIntStateOf(0) }
    val totalPages = 4
    val activity = LocalContext.current as ComponentActivity
    val showModal = remember { mutableStateOf(false) }
    val modalAccuracy = remember { mutableDoubleStateOf(0.0) }
    val countdownValue = remember { mutableIntStateOf(3) }
    val showCountdown = remember { mutableStateOf(true) }
    val lifecycleOwner = LocalLifecycleOwner.current
    val isLoading = remember { mutableStateOf(true) }
    val isText = remember { mutableStateOf("") }

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(activity) }
    val previewView = remember { PreviewView(activity) }
    val isCapturePage = remember { mutableStateOf(false) }

    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    val youtubeVideoIds = remember { mutableStateOf<List<String>>(emptyList()) }

    val context: Context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val authId = sharedPreferences.getLong("saved_authId", 0)

    // 데이터 로드 시작
    LaunchedEffect(eduId) {
        getEducationDetail(
            eduId = eduId,
            authorization = authId,
            onSuccess = { results ->
                val ids = results.mapNotNull { video ->
                    extractYouTubeVideoId(video.url)?.also { videoId ->
                        Log.d("EducationDetail", "Title: ${video.title}")
                        Log.d("EducationDetail", "URL: $videoId")
                        Log.d("EducationDetail", "Description: ${video.description}")
                    }
                }
                youtubeVideoIds.value = ids
                isLoading.value = false
            },
            onFailure = { message ->
                // 실패 시 토스트 메시지 표시
                toastMessage = message
                showToast = true
                isLoading.value = false
            },
        )
    }

    LaunchedEffect(currentPage.value) {
        showModal.value = false
        showCountdown.value = false
        viewModel.initAccuracy()
    }

    LaunchedEffect(showCountdown.value) {
        if (showCountdown.value) {
            for (i in 3 downTo 1) {
                countdownValue.value = i
                kotlinx.coroutines.delay(1000L)
            }
            showCountdown.value = false
            viewModel.startRecording(
                activity,
                onFinal = { accuracy ->
                    modalAccuracy.value = accuracy
                    isLoading.value = false
                },
                onText = { text ->
                    isText.value = text
                },
            )
            Log.d("CaptureVideoScreen", "start_capture")
        }
    }

    // 카메라 설정
    DisposableEffect(lifecycleOwner) {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }

        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        val videoCapture = VideoCapture.withOutput(Recorder.Builder().build())
        viewModel.setupVideoCapture(videoCapture)

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, videoCapture)
        } catch (e: Exception) {
            Log.e("CaptureVideoScreen", "Binding failed", e)
        }

        onDispose {
            cameraProvider.unbindAll()
        }
    }

    // 로딩 상태일 경우 로딩 인디케이터 표시
    if (isLoading.value) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        Scaffold(
            topBar = {
                TionTopAppBarBack(
                    title = {
                        Text(
                            text = viewModel.getVideoTitle(),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 15.dp),
                    onNavigationClick = onNavigateToBack,
                )
            },
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BoxWithConstraints {
                    val maxWidth = maxWidth
                    Column(
                        modifier = Modifier.width(maxWidth),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        if (currentPage.value > 0) {
                            if (showCountdown.value) {
                                TionModalText(countdownValue.value.toString())
                            }
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                (0 until totalPages).forEach { index ->
                                    Indicator(isSelected = currentPage.value == index + 1)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                        when (currentPage.value) {
                            0 -> {
                                if (youtubeVideoIds.value.isNotEmpty()) {
                                    TionYouTubePlayer(
                                        youtubeVideoId = youtubeVideoIds.value[currentPage.value],
                                        lifecycleOwner = lifecycleOwner,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        onVideoPlaying = {},
                                        onVideoEnded = {},
                                    )
                                    DescriptionTexts()
                                    Spacer(Modifier.height(70.dp))
                                    TionButton(
                                        onClick = {
                                            currentPage.value++
                                        },
                                        content = {
                                            Text(
                                                text = "다음",
                                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                                fontSize = 20.sp,
                                            )
                                        },
                                        modifier = Modifier
                                            .padding(32.dp)
                                            .width(300.dp)
                                            .height(75.dp),
                                    )
                                }
                            }

                            else -> {
                                if (!isCapturePage.value) {
                                    if (youtubeVideoIds.value.size > currentPage.value) {
                                        TionYouTubePlayer(
                                            youtubeVideoId = youtubeVideoIds.value[currentPage.value],
                                            lifecycleOwner = lifecycleOwner,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(200.dp),
                                            onVideoPlaying = {},
                                            onVideoEnded = {},
                                        )
                                    }
                                    Spacer(Modifier.height(175.dp))
                                    TionButton(
                                        onClick = {
                                            isCapturePage.value = true
                                            showCountdown.value = true
                                        },
                                        content = {
                                            Text(
                                                text = "동작 촬영하기",
                                                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                                fontSize = 20.sp,
                                            )
                                        },
                                        modifier = Modifier
                                            .padding(32.dp)
                                            .width(300.dp)
                                            .height(75.dp),
                                    )
                                } else {
                                    AndroidView(
                                        factory = { previewView },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .clipToBounds(),
                                    )
                                    Spacer(Modifier.height(175.dp))
                                    Row {
                                        TionButton(
                                            onClick = {
                                                isCapturePage.value = false
                                                viewModel.cancelRecording()
                                                Log.d("CaptureVideoScreen", "cancel_capture")
                                            },
                                            content = {
                                                Text(
                                                    text = "이전",
                                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                                    fontSize = 20.sp,
                                                )
                                            },
                                            modifier = Modifier
                                                .padding(32.dp)
                                                .width(100.dp)
                                                .height(75.dp),
                                        )
                                        Spacer(Modifier.width(80.dp))
                                        TionButton(
                                            onClick = {
                                                showModal.value = true
                                                viewModel.stopRecording()
                                                Log.d("CaptureVideoScreen", "stop_capture")
                                            },
                                            content = {
                                                Text(
                                                    text = "완료",
                                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                                                    fontSize = 20.sp,
                                                )
                                            },
                                            modifier = Modifier
                                                .padding(32.dp)
                                                .width(100.dp)
                                                .height(75.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showToast) {
        TionToast(
            messageTxt = toastMessage,
            onDismiss = { showToast = false },
        )
    }

    if (showModal.value && currentPage.value != 0) {
        if (currentPage.value == 4) {
            onNavigateToResult(eduId)
        } else {
            if (modalAccuracy.value >= 90.0) {
                Log.d("Education", "잘했어요")
                TionAccuracyModal(
                    id = R.drawable.character_good,
                    text = "${isText.value} 동작 잘했어요!",
                    accuracy = modalAccuracy.value.toString(),
                    onMove = {
                        currentPage.value++
                        isCapturePage.value = false
                    },
                )
            } else if (modalAccuracy.value > 0.0) {
                Log.d("Education", "아쉬워요")
                TionAccuracyModal(
                    id = R.drawable.character_retry,
                    text = "${isText.value} 동작 조금 아쉬워요",
                    accuracy = modalAccuracy.value.toString(),
                    onMove = {
                        currentPage.value++
                        isCapturePage.value = false
                    },
                )
            } else {
                LoadingModal()
            }
        }
    }
}

@Composable
fun LoadingModal() {
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
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun DescriptionTexts() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = "1. 두 손을 들어 올려요.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 80.dp),
        )
        Text(
            text = "2. 두 손을 마주 치며 소리를 내요.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp),
        )
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(10.dp)
            .clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onTertiary),
    )
}
