package com.example.androidblossomingchildren.libraries.network.dataClass

data class LoginResponse(
    val status: Int,
    val message: String,
    val result: LoginResult,
)
