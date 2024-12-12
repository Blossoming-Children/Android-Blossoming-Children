package com.example.androidblossomingchildren.ui.config

import com.example.androidblossomingchildren.libraries.network.dataClass.EducationResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.EducationResult
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getEducationInfo(
    authorization: Long,
    onSuccess: (List<EducationResult>) -> Unit,
    onFailure: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val call = service.education(
        authorization = authorization,
    )

    call.enqueue(
        object : Callback<EducationResponse> {
            override fun onResponse(
                call: Call<EducationResponse>,
                response: Response<EducationResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.status == 200) {
                        if (responseBody.result != null) {
                            onSuccess(responseBody.result)
                        }
                    } else {
                        // 응답을 가져오는 데 실패한 경우 서버에서 전달한 메시지 사용
                        onFailure(responseBody?.message ?: "실패했습니다.")
                    }
                } else {
                    // 서버 응답이 실패 상태 코드일 때 처리
                    onFailure("서버 연결에 실패했습니다. 오류 코드: ${response.body()?.status}")
                }
            }

            override fun onFailure(call: Call<EducationResponse>, t: Throwable) {
                onFailure("네트워크 오류: ${t.message}")
            }
        },
    )
}
