package com.example.androidblossomingchildren.ui.config

import android.util.Log
import com.example.androidblossomingchildren.libraries.network.dataClass.UpdateGoalRequest
import com.example.androidblossomingchildren.libraries.network.dataClass.UpdateGoalResponse
import com.example.androidblossomingchildren.libraries.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun updateGoal(
    userId: Int,
    targetStamp: Int,
    goalDetail: String,
//    onSuccess: (String) -> Unit,
//    onFailure: (String) -> Unit
) {
    val service = RetrofitClient.instance
    val call = service.updateGoal(
        authorization = userId,
        request = UpdateGoalRequest(targetStamp, goalDetail),
    )

    call.enqueue(object : Callback<UpdateGoalResponse> {
        override fun onResponse(call: Call<UpdateGoalResponse>, response: Response<UpdateGoalResponse>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.status == 200) {
                        Log.d("Stamp", it.message)
                        // onSuccess(it.message)
                    } else {
                        Log.d("Stamp", it.message)
                        // onFailure(it.message)
                    }
                } ?: Log.d("Stamp", "응답이 올바르지 않습니다.") // ?: onFailure("응답이 올바르지 않습니다.")
            } else {
                Log.d("Stamp", "서버 응답 오류: ${response.code()}")
                // onFailure("서버 응답 오류: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<UpdateGoalResponse>, t: Throwable) {
            Log.d("Stamp", "네트워크 오류: ${t.message}")
            // onFailure("네트워크 오류: ${t.message}")
        }
    })
}
