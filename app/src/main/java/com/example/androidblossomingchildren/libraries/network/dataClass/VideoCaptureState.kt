package com.example.androidblossomingchildren.libraries.network.dataClass

import androidx.camera.video.Recording

data class VideoCaptureState(
    val isButtonEnabled: Boolean = true,
    val recording: Recording? = null,
)
