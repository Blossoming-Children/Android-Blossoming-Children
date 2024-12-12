package com.example.androidblossomingchildren.libraries.network.retrofit

import com.example.androidblossomingchildren.libraries.network.dataClass.EducationDetailResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.EducationResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.FindEmailResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.LoginRequest
import com.example.androidblossomingchildren.libraries.network.dataClass.LoginResponse
import com.example.androidblossomingchildren.libraries.network.dataClass.SignUpRequest
import com.example.androidblossomingchildren.libraries.network.dataClass.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {
    @POST("/api/auth/sign-in") // 로그인 엔드포인트
    fun login(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authorization: String,
        @Body request: LoginRequest,
    ): Call<LoginResponse>

    @POST("/api/auth/sign-up")
    fun signUp(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authorization: String,
        @Body request: SignUpRequest,
    ): Call<SignUpResponse>

    @GET("/api/auth/find-email")
    fun findEmail(
        @Header("Content-Type") contentType: String = "application/json",
        @Query("email") email: String,
    ): Call<FindEmailResponse>

    @GET("/api/education")
    fun education(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authorization: Long,
    ): Call<EducationResponse>

    @GET("/api/education/{eduId}")
    fun getEducationDetails(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") authorization: Long,
        @Path("eduId") eduId: Int,
    ): Call<EducationDetailResponse>

//    @GET("/stamp")
//    fun getStampBoard(
//        @Header("Content-Type") contentType: String = "application/json",
//        @Header("Authorization") authorization: Long,
//    ): Call<StampBoardResponse>
//
//    @POST("/stamp")
//    fun addStamp(
//        @Header("Content-Type") contentType: String = "application/json",
//        @Header("Authorization") authorization: Int,
//    ): Call<AddStampResponse>
//
//    @PATCH("/stamp")
//    fun updateGoal(
//        @Header("Content-Type") contentType: String = "application/json",
//        @Header("Authorization") authorization: Int,
//        @Body request: UpdateGoalRequest,
//    ): Call<UpdateGoalResponse>
}
