package com.example.androidblossomingchildren.ui.viewmodel

import android.Manifest
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.androidblossomingchildren.libraries.network.dataClass.VideoCaptureState
import com.example.androidblossomingchildren.ui.config.getPathFromUri
import com.example.androidblossomingchildren.ui.config.uploadAndClassifyVideo
import com.example.androidblossomingchildren.ui.config.uploadVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale

class VideoDetailViewModel : ViewModel() {
    // accuracy 값을 저장하는 리스트
    private val _accuracyList = mutableListOf<Double>()
    private val _accuracy = mutableDoubleStateOf(0.0)

    // accuracy 리스트에 값을 추가하는 함수
    fun addAccuracy(accuracy: Double) {
        _accuracyList.add(accuracy)
    }

    fun initAccuracy() {
        Log.d("Accuracy2", _accuracy.value.toString())
        _accuracy.value = 0.0
    }

    // 평균 accuracy를 계산하는 함수
    fun getAccuracyAverage(): Double {
        return if (_accuracyList.isNotEmpty()) {
            _accuracyList.average()
        } else {
            0.0
        }
    }

    // 리스트 초기화 함수 (필요시 사용)
    fun clearAccuracyList() {
        _accuracyList.clear()
    }

    private val _videoCaptureState = MutableStateFlow(VideoCaptureState())
    val videoCaptureState: StateFlow<VideoCaptureState> = _videoCaptureState

    private var isCancelRecording: Boolean = false

    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    fun setupVideoCapture(videoCapture: VideoCapture<Recorder>) {
        this.videoCapture = videoCapture
    }

    fun startRecording(
        context: Context,
        onFinal: (Double) -> Unit,
        onText: (String) -> Unit,
    ) {
        initAccuracy()
        val videoCapture = this.videoCapture ?: return
        val fileNameFormat = "yyyy-MM-dd-HH-mm-ss-SSS"
        val name = SimpleDateFormat(fileNameFormat, Locale.US).format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")
            }
        }

        val mediaStoreOutputOptions = MediaStoreOutputOptions
            .Builder(context.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()

        recording = videoCapture.output
            .prepareRecording(context, mediaStoreOutputOptions)
            .apply {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.RECORD_AUDIO,
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    withAudioEnabled()
                }
            }
            .start(ContextCompat.getMainExecutor(context)) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> {
                        _videoCaptureState.value =
                            videoCaptureState.value.copy(isButtonEnabled = true)
                    }

                    is VideoRecordEvent.Finalize -> {
                        if (!recordEvent.hasError() && !isCancelRecording) {
                            val msg = "Video capture succeeded: ${recordEvent.outputResults.outputUri}"
                            // Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            Log.d(TAG, msg)

                            val videoUri = recordEvent.outputResults.outputUri
                            val videoPath = getPathFromUri(context, videoUri)

//                            uploadVideoToServer(
//                                recordEvent.outputResults.outputUri.toString(),
//                                onSuccess = { accuracy ->
//                                    addAccuracy(accuracy)
//                                    onFinal(accuracy)
//                                },
//                                onFailure = { errorMessage ->
//                                    Log.e("Upload", errorMessage)
//                                }
//                            )
                            if (videoPath != null) {
                                uploadVideo(
                                    videoPath,
                                    onSuccess = { accuracy ->
                                        addAccuracy(accuracy)
                                        onFinal(accuracy)
                                    },
                                )
                                uploadAndClassifyVideo(
                                    videoPath,
                                    onSuccess = { text ->
                                        onText(text)
                                    },
                                )
                            } else {
                                Log.e(TAG, "비디오 파일 변환 실패")
                            }
                        } else {
                            isCancelRecording = false
                            recording?.close()
                            recording = null
                            Log.e(TAG, "Video capture ends with error: ${recordEvent.error}")
                        }
                        _videoCaptureState.value =
                            videoCaptureState.value.copy(isButtonEnabled = true)
                    }
                }
            }

        _videoCaptureState.value =
            videoCaptureState.value.copy(isButtonEnabled = false, recording = recording)
    }

    fun stopRecording() {
        initAccuracy()
        recording?.stop()
        recording = null
        _videoCaptureState.value =
            videoCaptureState.value.copy(isButtonEnabled = false, recording = null)
    }

    fun cancelRecording() {
        initAccuracy()
        isCancelRecording = true
        recording?.stop()
        recording = null
        _videoCaptureState.value =
            videoCaptureState.value.copy(isButtonEnabled = false, recording = null)
    }

    private var _videoTitle = mutableStateOf("박수 짝짝!")

    fun getVideoTitle(): String {
        return _videoTitle.value
    }

    fun updateVideoTitle(title: String) {
        _videoTitle.value = title
    }
}
