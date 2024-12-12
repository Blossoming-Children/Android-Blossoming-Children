package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.LoginRequest
import com.example.androidblossomingchildren.libraries.network.dataClass.LoginResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun loginUser(
    email: String,
    password: String,
    refreshToken: String,
    onSuccess: (Long) -> Unit,
    showToast: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val call = service.login(
        authorization = "Bearer $refreshToken",
        request = LoginRequest(email, password),
    )

    call.enqueue(
        object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("Response Status: ", response.body()?.status.toString())
                if (response.isSuccessful && response.body()?.status == 200) {
                    val authId = response.body()?.result?.authId
                    if (authId != null) {
                        onSuccess(authId)
                        Log.d("Login right before: ", "authId: $authId")
                    }
                    showToast("로그인에 성공했습니다.")
                } else {
                    showToast("회원정보가 일치하지 않습니다.")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("Login", "로그인 실패: ${t.message}")
            }
        },
    )
}
