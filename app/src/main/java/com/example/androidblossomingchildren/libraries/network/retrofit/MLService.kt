package com.example.androidblossomingchildren.libraries.network.retrofit

import com.example.androidblossomingchildren.libraries.network.dataClass.ClassificationResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.ConsistencyResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
interface MLService {
    @Multipart
    @POST("/api/education/consistency")
    fun uploadVideo(
        @Part education_video: MultipartBody.Part,
    ): Call<ConsistencyResponse>

    @Multipart
    @POST("api/education/classify")
    fun classifyVideo(
        @Part video: MultipartBody.Part,
    ): Call<ClassificationResponse>
}
