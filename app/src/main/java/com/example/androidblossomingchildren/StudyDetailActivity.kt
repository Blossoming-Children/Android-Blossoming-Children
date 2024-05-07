package com.example.androidblossomingchildren

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.androidblossomingchildren.util.component.TionButton
import com.example.androidblossomingchildren.util.theme.TionTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import android.Manifest
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext

class StudyDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemText = intent.getStringExtra("ITEM_TEXT") ?: "기본값"

        setContent {
            TionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DetailScreen(this@StudyDetailActivity, itemText)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(activity: ComponentActivity, itemText: String) {
    val currentPage = remember { mutableStateOf(0) }
    val totalPages = 4

    Scaffold(
        topBar = {
            DetailTopAppBar(title = itemText, onBackClicked = { activity.finish() })
        },
        content = { padding ->
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
                                YouTubePlayer(youtubeVideoId = "4SYi6EBeAr8", lifecycleOwner = activity)
                                DescriptionTexts()
                            }
                            else -> {
                                YouTubePlayer(
                                    youtubeVideoId = "4SYi6EBeAr8",
                                    lifecycleOwner = activity,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16 / 9f)
                                )
                                CameraXPreview(activity = activity)
                            }
                        }
                    }
                }

                TionButton(
                    onClick = {
                        if (currentPage.value < totalPages) {
                            currentPage.value++
                        }
                    },
                    content = {
                        Text(
                            text = if (currentPage.value == totalPages) "완료" else "다음",
                            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
                            fontSize = 20.sp,
                        )
                    },
                    modifier = Modifier
                        .padding(32.dp)
                        .width(300.dp),
                )
            }
        },
    )
}

@Composable
fun DescriptionTexts() {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "1. 1번 설명입니다.1",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 50.dp),
        )
        Text(
            text = "2. 2번 설명입니다.22",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "3. 3번 설명입니다.333",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
        Text(
            text = "4. 4번 설명입니다.4444",
            fontFamily = FontFamily(Font(R.font.laundrygothic_regular)),
            fontSize = 30.sp,
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(title: String, onBackClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        title = {
            Text(
                text = title,
                fontFamily = FontFamily(Font(R.font.laundrygothic_bold)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
            )
        },
    )
}

@Composable
fun YouTubePlayer(youtubeVideoId: String, lifecycleOwner: LifecycleOwner, modifier : Modifier = Modifier) {
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
fun CameraXPreview(activity: ComponentActivity) {
    val context = LocalContext.current
    val hasCameraPermission = remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
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
        }
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

                cameraProviderFuture.addListener({
                    val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            activity, CameraSelector.DEFAULT_FRONT_CAMERA, preview)
                    } catch (exc: Exception) {
                        Log.e("CameraPreview", "Use case 바인딩 실패", exc)
                    }
                }, ContextCompat.getMainExecutor(context))

                previewView
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(top = 16.dp)
                .clipToBounds()
        )
    }
}
