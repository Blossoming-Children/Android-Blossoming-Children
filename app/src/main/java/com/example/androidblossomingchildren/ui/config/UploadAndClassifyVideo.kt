package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.ClassificationResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitML
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

fun uploadAndClassifyVideo(
    filePath: String,
    onSuccess: (String) -> Unit,
) {
    val videoFile = File(filePath)
    val requestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), videoFile)
    val videoPart = MultipartBody.Part.createFormData("education_video", videoFile.name, requestBody)

    val service = RetrofitML.instance
    val call = service.classifyVideo(videoPart)

    call.enqueue(object : Callback<ClassificationResponse> {
        override fun onResponse(call: Call<ClassificationResponse>, response: Response<ClassificationResponse>) {
            if (response.isSuccessful) {
                val classification = response.body()?.classification
                Log.d("API_SUCCESS", "Classified as: $classification")
                onSuccess(classification.toString())
            } else {
                Log.e("API_ERROR", "Failed to classify video: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<ClassificationResponse>, t: Throwable) {
            Log.e("API_FAILURE", "Error: ${t.message}")
        }
    })
}
