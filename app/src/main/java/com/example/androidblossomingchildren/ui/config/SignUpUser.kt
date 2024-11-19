package com.example.androidblossomingchildren.ui.config

import com.example.androidblossomingchildren.libraries.network.dataClass.SignUpRequest
import com.example.androidblossomingchildren.libraries.network.dataClass.SignUpResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun signUpUser(
    email: String,
    password: String,
    nickname: String,
    refreshToken: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val request = SignUpRequest(email, password, nickname)
    val authorizationHeader = "Bearer $refreshToken"

    val call = service.signUp(
        authorization = authorizationHeader,
        request = request,
    )

    call.enqueue(object : Callback<SignUpResponse> {
        override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.status == 201) {
                    // 회원가입 성공 시 호출
                    onSuccess()
                } else {
                    // 회원가입 실패 시 서버에서 전달한 메시지 사용
                    onFailure(responseBody?.message ?: "회원가입에 실패했습니다.")
                }
            } else {
                // 서버 응답이 실패 상태 코드일 때 처리
                onFailure("회원가입에 실패했습니다. 오류 코드: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
            // 네트워크 오류 등으로 인해 실패한 경우
            onFailure("네트워크 오류: ${t.message}")
        }
    })
}
