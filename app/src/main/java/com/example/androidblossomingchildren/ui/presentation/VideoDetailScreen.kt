@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidblossomingchildren.ui.presentation

import android.Manifest
import android.content.pm.PackageManager
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.androidblossomingchildren.DescriptionTexts
import com.example.androidblossomingchildren.R
import com.example.androidblossomingchildren.ui.components.TionModal
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.component.TionTopAppBarBack
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VideoDetailScreen(
    videoId: String,
    onNavigateToResult: (Any?) -> Unit,
    onNavigateToBack: () -> Unit,
) {
    val currentPage = remember { mutableStateOf(0) }
    val totalPages = 4
    val activity = LocalContext.current as ComponentActivity
    val showModal = remember { mutableStateOf(false) }
    val modalAccuracy = remember { mutableStateOf(0.8f) }

    val context = LocalContext.current
    val exoplayer = ExoPlayer.Builder(context).build()
    val mediaSource =
        remember {
            mutableStateOf(MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_total}"))
        }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(isLoading.value) {
        exoplayer.setMediaItem(mediaSource.value)
        exoplayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoplayer.release()
        }
    }

    LaunchedEffect(currentPage.value) {
        isLoading.value = true
        exoplayer.release()
        if (currentPage.value == 2) {
            modalAccuracy.value = 0.5f
            delay(7000L)
            showModal.value = true
        } else if (currentPage.value > 0 && !showModal.value) {
            modalAccuracy.value = 0.8f
            delay(7000L)
            showModal.value = true
        }
    }

    Scaffold(
        topBar = {
            TionTopAppBarBack(
                title = {
                    Text(
                        text = videoId,
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
        content = { padding ->
            if (showModal.value) {
                TionModal(
                    accuracy = modalAccuracy.value,
                    isLastPage = currentPage.value == totalPages,
                    onDismiss = {
                        if (currentPage.value == totalPages) {
                            onNavigateToResult(videoId)
                        } else if (currentPage.value == 2 && modalAccuracy.value == 0.5f) {
                            modalAccuracy.value = 0.8f
                            showModal.value = false

                            activity.lifecycleScope.launch {
                                delay(7000L)
                                showModal.value = true
                            }

                            exoplayer.play()
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
                        // 인디케이터

                        if (isLoading.value) {
                            CircularProgressIndicator()
                        } else {
                            when (currentPage.value) {
                                0 -> {
                                    AndroidView(
                                        factory = { ctx ->
                                            PlayerView(ctx).apply {
                                                player = exoplayer
                                                exoplayer.playWhenReady = true
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                    )
                                    DescriptionTexts()
                                    Spacer(Modifier.height(70.dp))
                                    TionButton(
                                        onClick = {
                                            if (currentPage.value == 0) {
                                                currentPage.value++
                                            }
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

                                else -> {
                                    AndroidView(
                                        factory = { ctx ->
                                            PlayerView(ctx).apply {
                                                player = exoplayer
                                                exoplayer.playWhenReady = true
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                    )
                                    Spacer(Modifier.height(175.dp))
                                    CameraXPreview(activity = activity)
                                }
                            }
                        }
//                        TionButton(
//                            onClick = {
//                                if (currentPage.value == 0) {
//                                currentPage.value++
//                                }
//                            },
//                            content = {
//                                Text(
//                                    text = "다음",
//                                    fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
//                                    fontSize = 20.sp,
//                                )
//                            },
//                            modifier = Modifier
//                                .padding(32.dp)
//                                .width(300.dp)
//                                .height(75.dp),
//                        )

                        when (currentPage.value) {
                            0 -> {
                                mediaSource.value =
                                    MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_total}")
                                isLoading.value = false
                            }

                            1 -> {
                                mediaSource.value =
                                    MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_walk}")
                                isLoading.value = false
                            }

                            2 -> {
                                mediaSource.value =
                                    MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_clap}")
                                isLoading.value = false
                            }

                            3 -> {
                                mediaSource.value =
                                    MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_cheer}")
                                isLoading.value = false
                            }

                            4 -> {
                                mediaSource.value =
                                    MediaItem.fromUri("android.resource://com.example.androidblossomingchildren/${R.raw.sample_video_oneleg}")
                                isLoading.value = false
                            }
                        }
                    }
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
            text = "1. 1번 설명입니다.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 50.dp),
        )
        Text(
            text = "2. 2번 설명입니다.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "3. 3번 설명입니다.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "4. 4번 설명입니다.",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
    }
}

@Composable
fun YouTubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)),
        factory = {
            YouTubePlayerView(context = it).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(
                    object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(youtubeVideoId, 0f)
                        }
                    },
                )
            }
        },
    )
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
                .fillMaxWidth()
                .height(200.dp)
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
