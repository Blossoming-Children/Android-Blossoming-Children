package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.AddStampResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun addStamp(
    userId: Int,
) {
    val service = RetrofitClient.instance
    val call = service.addStamp(authorization = userId)

    call.enqueue(object : Callback<AddStampResponse> {
        override fun onResponse(call: Call<AddStampResponse>, response: Response<AddStampResponse>) {
            if (response.isSuccessful && response.body() != null) {
            } else {
                Log.d("Stamp", "response.body()?.status: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<AddStampResponse>, t: Throwable) {
            Log.d("StampAdd", "네트워크 요청 실패: ${t.message}")
        }
    })
}
