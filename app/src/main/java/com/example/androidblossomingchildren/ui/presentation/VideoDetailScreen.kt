@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.StudyResultActivity
import com.example.androidblossomingchildren.ui.components.TionModal
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VideoDetailScreen(
    videoId: String,
    onNavigateToBack: () -> Unit,
    navController: NavController,
) {
    val currentPage = remember { mutableStateOf(0) }
    val totalPages = 4
    val activity = LocalContext.current as ComponentActivity
    Log.d("Activity", activity.toString())
    val showModal = remember { mutableStateOf(false) }
    val modalAccuracy = remember { mutableStateOf(0.8f) }

    LaunchedEffect(currentPage.value) {
        if (currentPage.value == 2) {
            modalAccuracy.value = 0.5f
            delay(5000L)
            showModal.value = true
        } else if (currentPage.value > 0 && !showModal.value) {
            modalAccuracy.value = 0.8f
            delay(5000L)
            showModal.value = true
        }
    }

    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(text = "Video $videoId")
                },
                onNavigationClick = onNavigateToBack,
            )
        },
        content = { padding ->
            if (showModal.value) {
                TionModal(
                    accuracy = modalAccuracy.value,
                    isLastPage = currentPage.value == totalPages,
                    onDismiss = {
                        if (currentPage.value == totalPages) {
                            val intent = Intent(activity, StudyResultActivity::class.java)
                            intent.putExtra("ITEM_TEXT", videoId)
                            activity.startActivity(intent)
                        } else if (currentPage.value == 2 && modalAccuracy.value == 0.5f) {
                            modalAccuracy.value = 0.8f
                            showModal.value = false

                            activity.lifecycleScope.launch {
                                delay(5000L)
                                showModal.value = true
                            }
                        } else {
                            if (currentPage.value < totalPages) {
                                currentPage.value++
                                showModal.value = false
                            }
                        }
                    },
                )
            }

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

                        when (currentPage.value) {
                            0 -> {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        VideoView(videoUri = R.raw.studyvideo)
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        DescriptionTexts()
                                    }
                                }
                            }
                            1 -> {
                                VideoView(videoUri = R.raw.walking)
                                CameraXPreview(activity = activity)
                            }
                            2 -> {
                                VideoView(videoUri = R.raw.clap)
                                CameraXPreview(activity = activity)
                            }
                            3 -> {
                                VideoView(videoUri = R.raw.hurray)
                                CameraXPreview(activity = activity)
                            }
                            4 -> {
                                VideoView(videoUri = R.raw.jumping)
                                CameraXPreview(activity = activity)
                            }
                        }
                    }
                }
            }
            if (currentPage.value == 0) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    TionButton(
                        onClick = {
                            if (currentPage.value < totalPages) {
                                currentPage.value++
                            }
                        },
                        content = {
                            Text(
                                text = "다음",
                                fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                                fontSize = 20.sp,
                            )
                        },
                        modifier = Modifier
                            .padding(32.dp)
                            .width(300.dp)
                            .height(75.dp)
                    )
                }
            }
        },
    )
}

@Composable
fun DescriptionTexts() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = "1. 걷기",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "2. 손뼉 치기",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "3. 만세!",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "4. 한 발 들고 뛰기",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
    }
}

@Composable
fun CameraXPreview(activity: ComponentActivity) {
    val context = LocalContext.current
    val hasCameraPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA,
            ) == PackageManager.PERMISSION_GRANTED,
        )
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                Log.d("CameraPreview", "카메라 권한 부여")
            } else {
                Log.e("CameraPreview", "카메라 권한 거절")
            }
        },
    )

    LaunchedEffect(Unit) {
        if (!hasCameraPermission.value) {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission.value) {
        AndroidView(
            factory = { context ->
                val previewView = PreviewView(context)
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

                cameraProviderFuture.addListener(
                    {
                        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                activity,
                                CameraSelector.DEFAULT_FRONT_CAMERA,
                                preview,
                            )
                        } catch (exc: Exception) {
                            Log.e("CameraPreview", "Use case 바인딩 실패", exc)
                        }
                    },
                    ContextCompat.getMainExecutor(context),
                )

                previewView
            },
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
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

@Composable
fun VideoView(videoUri: Int) {
    val context = LocalContext.current
    val videoUri = Uri.parse("android.resource://${context.packageName}/$videoUri")

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            val exoPlayer = ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(videoUri)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = false
            }

            PlayerView(context).apply {
                player = exoPlayer
            }
        }
    )
}
