package com.example.androidblossomingchildren.libraries.network.retrofit

import android.util.Log
import com.example.androidblossomingchildren.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // private const val BASE_URL = "https://api.example.com/" /* TODO: 실제 API URL로 변경 */

    val instance: AuthService by lazy {
        Log.d("Base URL: ", BuildConfig.BASE_URL)
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }
}
