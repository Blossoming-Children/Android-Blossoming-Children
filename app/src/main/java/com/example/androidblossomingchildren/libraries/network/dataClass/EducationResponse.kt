package com.example.androidblossomingchildren.libraries.network.dataClass

data class EducationResponse(
    val status: Int,
    val message: String,
    val result: List<EducationResult>? = null,
)
