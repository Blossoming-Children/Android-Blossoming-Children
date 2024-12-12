package com.example.androidblossomingchildren.ui.config

import com.example.androidblossomingchildren.libraries.network.dataClass.EducationDetailResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.EducationDetailResult
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getEducationDetail(
    eduId: Int,
    authorization: Long,
    onSuccess: (List<EducationDetailResult>) -> Unit,
    onFailure: (String) -> Unit,
) {
    val service = RetrofitClient.instance
    val call = service.getEducationDetails(
        authorization = authorization,
        eduId = eduId,
    )

    call.enqueue(object : Callback<EducationDetailResponse> {
        override fun onResponse(
            call: Call<EducationDetailResponse>,
            response: Response<EducationDetailResponse>,
        ) {
            if (response.isSuccessful && response.body()?.status == 200) {
                val resultList = response.body()?.result
                if (resultList != null) {
                    onSuccess(resultList)
                } else {
                    onFailure(response.body()?.message ?: "No results found.")
                }
            } else {
                val errorMessage = response.body()?.message ?: "Unknown error occurred"
                onFailure("서버 연결에 실패했습니다. 오류 코드: ${response.body()?.status}")
            }
        }

        override fun onFailure(call: Call<EducationDetailResponse>, t: Throwable) {
            onFailure("네트워크 오류: ${t.message}")
        }
    })
}
