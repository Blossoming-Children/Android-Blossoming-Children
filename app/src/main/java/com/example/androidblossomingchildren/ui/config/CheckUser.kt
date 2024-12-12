package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.FindEmailResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun findEmail(
    email: String,
    onSuccess: (String) -> Unit,
    onFailure: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val call = service.findEmail(
        email = email,
    )

    call.enqueue(object : Callback<FindEmailResponse> {
        override fun onResponse(call: Call<FindEmailResponse>, response: Response<FindEmailResponse>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.status == 200) {
                    // 이메일 조회 성공 시 호출
                    onSuccess(responseBody.message)
                } else {
                    // 이메일 조회 실패 시 서버에서 전달한 메시지 사용
                    onFailure(responseBody?.message ?: "이메일 조회에 실패했습니다.")
                }
            } else {
                // 서버 응답이 실패 상태 코드일 때 처리
                onFailure(response.body()?.message.toString())
                Log.d("Response", response.toString())
            }
        }

        override fun onFailure(call: Call<FindEmailResponse>, t: Throwable) {
            // 네트워크 오류 등으로 인해 실패한 경우
            onFailure("네트워크 오류: ${t.message}")
        }
    })
}
