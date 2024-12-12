package com.example.androidblossomingchildren.ui.config

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.ConsistencyResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitML
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

fun uploadVideo(
    filePath: String,
    onSuccess: (Double) -> Unit,
) {
    val videoFile = File(filePath)

    // 비디오 파일을 RequestBody로 변환
    val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), videoFile)
    val videoPart = MultipartBody.Part.createFormData("education_video", videoFile.name, requestBody)

    val service = RetrofitML.instance
    val call = service.uploadVideo(videoPart)
    call.enqueue(object : retrofit2.Callback<ConsistencyResponse> {
        override fun onResponse(call: Call<ConsistencyResponse>, response: retrofit2.Response<ConsistencyResponse>) {
            if (response.isSuccessful) {
                val consistency = response.body()?.consistency
                Log.d("API_SUCCESS", "Consistency: $consistency")
                if (consistency != 0.0) {
                    val formattedValue = String.format("%.1f", consistency!! * 100)
                    onSuccess(formattedValue.toDouble())
                } else {
                    onSuccess(consistency)
                }
            } else {
                Log.e("API_ERROR", "Failed to upload video: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ConsistencyResponse>, t: Throwable) {
            Log.e("API_FAILURE", "Error: ${t.message}")
        }
    })
}

fun getPathFromUri(context: Context, uri: Uri): String? {
    var path: String? = null
    val projection = arrayOf(MediaStore.Video.Media.DATA)
    val cursor: Cursor? = context.contentResolver.query(uri, projection, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            path = it.getString(columnIndex)
        }
    }
    return path
}
