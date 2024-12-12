package com.example.androidblossomingchildren.libraries.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitML {
    private const val ML_URL = "https://"

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES) // 연결 시도 시간 설정 (5분)
        .writeTimeout(5, TimeUnit.MINUTES) // 요청 본문 쓰기 시간 설정 (5분)
        .readTimeout(5, TimeUnit.MINUTES) // 응답 읽기 시간 설정 (5분)
        .build()

    val instance: MLService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(ML_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        retrofit.create(MLService::class.java)
    }
}
