package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.StampBoardResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun fetchStampBoard(
    userId: Long,
    onSuccess: (StampBoardResponse) -> Unit,
    onFailure: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val call = service.getStampBoard(authorization = userId)

    call.enqueue(object : Callback<StampBoardResponse> {
        override fun onResponse(
            call: Call<StampBoardResponse>,
            response: Response<StampBoardResponse>,
        ) {
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {
                    onSuccess(it)
                }
            } else {
                Log.d("Stamp", "response.body()?.status: ${response.message()}")
                onFailure("서버 응답이 실패했습니다: ${response.message()}")
            }
        }

        override fun onFailure(call: Call<StampBoardResponse>, t: Throwable) {
            Log.d("Stamp", "네트워크 요청 실패: ${t.message}")
            onFailure("네트워크 요청 실패: ${t.message}")
        }
    })
}
